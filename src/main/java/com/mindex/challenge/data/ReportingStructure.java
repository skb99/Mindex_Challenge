package com.mindex.challenge.data;

public class ReportingStructure {
    private Integer numberOfReports;

    private Employee employee;

    public ReportingStructure(){
        // default number 0
        this.numberOfReports = 0;
        // default null employee
        this.employee = null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}