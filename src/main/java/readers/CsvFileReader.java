package readers;

import model.Employee;
import model.EmployeesContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// standard CSV (comma-separated values) are separated with commas and usually hasn't values in double quotation marks
// although sample data for the task is separated with semicolon and has values in double quotation marks
// both cases are supported

public class CsvFileReader extends AbstractReader {
    private final String salaryColumnName = "salary";
    private final String jobColumnName = "job";
    private String separator;
    private int jobColumnIndex;
    private int salaryColumnIndex;

    public CsvFileReader(String path) {
        super(path);
    }

    public EmployeesContainer read() {
        EmployeesContainer employeesContainer = new EmployeesContainer();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String firstLine = reader.readLine();
            separator = firstLine.contains(";") ? ";" : ",";
            salaryColumnIndex = getColumnIndex(firstLine,salaryColumnName);
            jobColumnIndex = getColumnIndex(firstLine,jobColumnName);
            int numberOfValues = getNumberOfValues(firstLine);
            String line;
            while((line = reader.readLine())!=null) {
                if(line.equals(""))
                    continue;
                employeesContainer.add(parseLine(line,numberOfValues));
            }
            return employeesContainer;
        } catch (IOException | ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException e) {
            return new EmployeesContainer();
        }
    }

    public Employee parseLine(String line, int length) throws IllegalArgumentException {
        String[] lineValues = line.split(separator);
//        for(String s : lineValues)
//            System.out.println(s);
//        System.out.println(separator + " len: " + length + " -> " + lineValues.length );
        try{
            if(length == lineValues.length)
                return new Employee( replaceQuotationMarksAndTrim(lineValues[jobColumnIndex]),
                        replaceQuotationMarksAndTrim(lineValues[salaryColumnIndex]));
            else {
                StringBuilder salary = new StringBuilder();
                salary.append(replaceQuotationMarksAndTrim(lineValues[salaryColumnIndex]));
                salary.append(".");
                salary.append(replaceQuotationMarksAndTrim(lineValues[salaryColumnIndex+1]));
                return new Employee(
                        replaceQuotationMarksAndTrim(lineValues[jobColumnIndex]),
                        salary.toString());
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    private String replaceQuotationMarksAndTrim(String text) {
        return text.replaceAll("\"", "").trim();
    }

    public int getColumnIndex(String firstLine, String columnName) throws IllegalArgumentException {
        int index=0;
        if(firstLine==null)
            throw new IllegalArgumentException();
        String[] columns = firstLine.split(separator);
        for(String s : columns) {
            if(s.toLowerCase().contains(columnName))
                return index;
            index++;
        }
        return -1;
    }

    private int getNumberOfValues(String firstLine) {
        return firstLine.split(separator).length;
    }

}
