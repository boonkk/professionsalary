package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class EmployeesContainer{
    @JsonProperty
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
    public void add(Employee employee) {
        employees.add(employee);
    }
    public EmployeesContainer(List<Employee> employees) {
        this.employees = employees;
    }
    public EmployeesContainer(){employees = new ArrayList<>();}

}
