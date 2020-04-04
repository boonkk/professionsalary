import model.Employee;
import org.junit.Assert;
import org.junit.Test;
import model.EmployeesContainer;
import readers.JsonFileReader;

public class JsonFileReaderTest {
    @Test
    public void jsonTest() {
        String path = "jsonTestFile.json";

        EmployeesContainer employeesContainer = new EmployeesContainer();
        employeesContainer.add(new Employee("Teacher","3540,20"));
        employeesContainer.add(new Employee("Janitor","13460.45"));
        employeesContainer.add(new Employee("Priest","15240.00"));
        employeesContainer.add(new Employee("Teacher","2700,10"));
        employeesContainer.add(new Employee("Janitor","13460,45"));

        JsonFileReader jsonFileReader = new JsonFileReader(path);
        EmployeesContainer readContainer = jsonFileReader.read();

        int index=0;
        for(Employee e : readContainer.getEmployees()) {
            Employee testEmp = employeesContainer.getEmployees().get(index);
            if(e.equals(testEmp))
                index++;
            else {
                System.out.println(e.equals(testEmp));
                Assert.fail();
            }
        }
    }

    @Test
    public void emptyJsonTest() {
        String[] emptyJsonPaths = {
                "emptyJsonTestFile1.json",
                "emptyJsonTestFile2.json",
                "emptyJsonTestFile3.json" };
        for(String path : emptyJsonPaths) {
            JsonFileReader jsonFileReader = new JsonFileReader(path);
            Assert.assertTrue(jsonFileReader.read().getEmployees().isEmpty());
        }
    }

    @Test
    public void invalidJsonTest() {
        String[] emptyJsonPaths ={
                "invalidJson1.json",
                "invalidJson2.json",
                "invalidJson3.json" };
        for(String path : emptyJsonPaths) {
            JsonFileReader jsonFileReader = new JsonFileReader(path);
            Assert.assertTrue(jsonFileReader.read().getEmployees().isEmpty());
        }
    }


}
