package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private String job;
    private String salary;
    /* Rest of the fields: id, name and surname are not included (not necessary), but can be easily added along
    with getters so Employee object can be fully deserialized from json.
     */

    public Employee(){}
    public Employee(String job, String salary) {
        this.job = job;
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public String getSalary() {
        return salary;
    }

    //for testing purposes
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Employee) {
            boolean areJobsEqual = ((Employee) obj).getJob().equalsIgnoreCase(job);
            try {
                BigDecimal salaryDot = new BigDecimal(salary.replaceAll(",", "."));
                BigDecimal objSalaryDot = new BigDecimal( ((Employee) obj).getSalary().replaceAll(",", "."));
                return objSalaryDot.compareTo(salaryDot) == 0 && areJobsEqual;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
