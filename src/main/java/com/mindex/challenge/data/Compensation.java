package com.mindex.challenge.data;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

//Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate.

public class Compensation {

    private Employee employee;

    private Float salary;

    @DateTimeFormat(style = "yyyy-MM-dd")
    private Date effectiveDate;


    public Compensation() {
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}