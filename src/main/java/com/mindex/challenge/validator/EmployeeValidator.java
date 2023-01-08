package com.mindex.challenge.validator;

import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeValidator {
    // Validation for creating New Employees
    public void validateEmployee(Employee newEmployee){

        if(newEmployee==null || newEmployee.equals(new Employee())) {
            throw new RuntimeException("Employee Information is missing");
        }

        if(newEmployee.getFirstName() == null) {
            throw new RuntimeException("First Name is not provided/ is null");
        }

        if(newEmployee.getLastName() == null) {
            throw new RuntimeException("Last Name is not provided/ is null");
        }

        if(newEmployee.getDepartment() == null) {
            throw new RuntimeException("Employee Departmant is not provided/ is null");
        }
    }
}
