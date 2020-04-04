import model.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {
    @Test
    public void equalsTest() {
        Employee employee1 = new Employee("priesT","292,39");
        Employee employee2 = new Employee("PriEst", "292.39");

        Employee employee3 = new Employee("DireCtor", "2943.");
        Employee employee4 = new Employee("dirEctOR", "2943,0000");

        Assert.assertEquals(employee1,employee2);
        Assert.assertEquals(employee3,employee4);
    }
}
