import com.sun.istack.internal.NotNull;
import consolewriters.ConsoleWriter;
import model.Employee;
import model.EmployeesContainer;
import org.apache.tika.Tika;
import readers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            int fileTypeIndicator = checkFileType(f.getPath());
            if(fileTypeIndicator == -1 || fileTypeIndicator == 1)
                sb.append(f.getPath()).append("####@");
        }
        return sb.toString().split("####@");
    }

    public void start() {
        for (String filePath : filePaths) {
            jobSalaries = new HashMap<>();
            AbstractReader contentReader;
            switch (checkFileType(filePath)) {
                case -1:
                    contentReader = new CsvFileReader(filePath);
                    break;
                case 1:
                    contentReader = new JsonFileReader(filePath);
                    break;
                default:
                    continue;
            }
            EmployeesContainer employeesContainer = contentReader.read();
            if( employeesContainer.getEmployees().size() == 0 ) {
                ConsoleWriter.writeInvalidContent(filePath);
                continue;
            }
            jobSalaries = calculateSalaries(employeesContainer);
            ConsoleWriter.writeMap(filePath,jobSalaries);
        }
    }

    //returns -1 for csv, 1 for json file
    private int checkFileType(String filePath)  {
        File file = new File(filePath);
        Tika tika = new Tika();
        String fileType = null;
        try {
            fileType = tika.detect(file);
        } catch(FileNotFoundException e ) {
            ConsoleWriter.writeFileNotFound(filePath);
        } catch (IOException e) {
            ConsoleWriter.writeInvalidContent(filePath);
        }
        if(fileType==null)
            return 0;
        if(fileType.equalsIgnoreCase("text/csv"))
            return -1;
        else if(fileType.equalsIgnoreCase("application/json"))
            return 1;
        else return 0;
    }

    private Map<String,BigDecimal> calculateSalaries(@NotNull EmployeesContainer container) {
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

    private BigDecimal getBigDecimal (@NotNull String stringNumber) {
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
