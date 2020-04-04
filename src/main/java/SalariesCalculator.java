import consolewriters.ConsoleWriter;
import model.Employee;
import model.EmployeesContainer;
import readers.*;
import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SalariesCalculator {
    private final String[] filePaths;
    private Map<String, BigDecimal> jobSalaries;

    public SalariesCalculator(String[] filePaths) {
        if(filePaths.length==0)
            this.filePaths = getFilesFromDirectory();
        else
            this.filePaths = filePaths;
    }

    public String[] getFilesFromDirectory() {
        File[] files = new File(".\\src\\main\\java\\data").listFiles();
        StringBuilder sb = new StringBuilder();
        for(File f : files) {
                sb.append(f.getPath()).append("####@");
        }
        return sb.toString().split("####@");
    }

    public void start() {
        for (String filePath : filePaths) {
            jobSalaries = new HashMap<>();
            AbstractReader contentReader;
            EmployeesContainer employeesContainer;
            try {
                contentReader = new CsvFileReader(filePath);
                employeesContainer = contentReader.read();
                if( employeesContainer.getEmployees().size() == 0 ) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                contentReader = new JsonFileReader(filePath);
                employeesContainer = contentReader.read();
                if( employeesContainer.getEmployees().size() == 0 ) {
                    ConsoleWriter.writeInvalidContent(filePath);
                    continue;
                }
            }

            jobSalaries = calculateSalaries(employeesContainer);
            ConsoleWriter.writeMap(filePath,jobSalaries);
        }
    }

    private Map<String,BigDecimal> calculateSalaries(EmployeesContainer container) {
        for(Employee emp : container.getEmployees()) {
            if(emp == null)
                continue;
            String jobKey = firstLetterCapital(emp.getJob());
            if(jobSalaries.containsKey(jobKey)) {
                jobSalaries.put(jobKey, jobSalaries.get(jobKey).add(getBigDecimal(emp.getSalary())));
            } else {
                jobSalaries.put(jobKey,getBigDecimal(emp.getSalary()));
            }
        }
        return jobSalaries;
    }

    private String firstLetterCapital(String text) {
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private BigDecimal getBigDecimal (String stringNumber) {
        if(stringNumber == null)
            return BigDecimal.ZERO;
        String resultString = stringNumber.trim().replace(",",".");
        try {
            return new BigDecimal(resultString);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    //test purposes
    protected Map<String, BigDecimal> getJobSalaries() {
        return jobSalaries;
    }
}
