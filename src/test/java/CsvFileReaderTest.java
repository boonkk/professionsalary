import org.junit.Assert;
import org.junit.Test;
import readers.CsvFileReader;
import model.Employee;
import model.EmployeesContainer;

public class CsvFileReaderTest {

    @Test
    public void semicolonCsvReadTest(){
        String path = ".\\src\\main\\resources\\uglyCSVSemicolon.csv";
        EmployeesContainer employeesContainer = new EmployeesContainer();
        employeesContainer.add(new Employee("pRIeST","200,0"));
        employeesContainer.add(new Employee("priest","2000,00"));
        employeesContainer.add(new Employee("Priest","15220.00"));

        CsvFileReader csvFileReader = new CsvFileReader(path);
        EmployeesContainer readContainer = csvFileReader.read();

        int index=0;
        for(Employee emp : readContainer.getEmployees()) {
            Employee testEmp = employeesContainer.getEmployees().get(index);
           if(emp.equals(testEmp))
               index++;
           else {
               System.out.println(emp.equals(testEmp));
               Assert.fail();
           }
        }
    }

    @Test
    public void commaCsvReadTest(){
        String path = ".\\src\\main\\resources\\uglyCSVComma.csv";

        EmployeesContainer employeesContainer = new EmployeesContainer();
        employeesContainer.add(new Employee("pRIeST","200,0"));
        employeesContainer.add(new Employee("priest","2000,00"));
        employeesContainer.add(new Employee("Teacher","60.1"));
        employeesContainer.add(new Employee("prieST","9000."));

        CsvFileReader csvFileReader = new CsvFileReader(path);
        EmployeesContainer readContainer = csvFileReader.read();
        int index=0;
        for(Employee e : readContainer.getEmployees()) {
            Employee testEmp = employeesContainer.getEmployees().get(index);
            if(e.equals(testEmp))
                index++;
            else {
                Assert.fail();
            }
        }
    }

    @Test
    public void emptyCsvTest() {
        String path = ".\\src\\main\\resources\\emptyCSVFile.csv";
        CsvFileReader csvFileReader = new CsvFileReader(path);
        EmployeesContainer container = csvFileReader.read();
        Assert.assertTrue(container.getEmployees().isEmpty());
    }

    @Test
    public void invalidCsvTest() {
        String[] invalidCsvPaths = {
                ".\\src\\main\\resources\\invalidCsv1.csv",
                ".\\src\\main\\resources\\invalidCsv2.csv" };
        for(String path : invalidCsvPaths) {
            CsvFileReader csvFileReader = new CsvFileReader(path);
            Assert.assertTrue(csvFileReader.read().getEmployees().isEmpty());
        }
    }
}
