import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SalariesCalculatorTest {



    @Test
    public void calculateSalariesTest() {
        String[] path = {"C:\\Users\\marci\\IdeaProjects\\professionsalary\\src\\main\\resources\\jsonTestFile.json"};
        SalariesCalculator salariesCalculator = new SalariesCalculator(path);
        salariesCalculator.start();
        Map<String, BigDecimal> calculationResult = salariesCalculator.getJobSalaries();

        Map<String,BigDecimal> calculations = new HashMap<>();
        calculations.put("Teacher",new BigDecimal("6240.30"));
        calculations.put("Janitor",new BigDecimal("26920.9"));
        calculations.put("Priest",new BigDecimal("15240.00"));

        calculationResult.entrySet().forEach(
                e -> Assert.assertEquals(
                        calculations.get(e.getKey()).compareTo(e.getValue()),0));
    }

//    @Test
//    public void calculateEmptySalariesTest() {
//        String[] path
//    }


}
