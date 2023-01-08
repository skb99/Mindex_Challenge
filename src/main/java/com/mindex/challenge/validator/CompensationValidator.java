package com.mindex.challenge.validator;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CompensationValidator {
    // Method to validate new compensation entries
    public void validateCompensation(Compensation enteredCompensation) {
        if(StringUtils.isEmpty(StringUtils.trimAllWhitespace(enteredCompensation.getEmployee().getEmployeeId()))) {
            throw new RuntimeException("Employee Id  is not provided/ is null");
        }
        if(enteredCompensation.getEmployee()==null || enteredCompensation.getEmployee().equals(new Employee())) {
            throw new RuntimeException("Employee Information is missing");
        }
        if(enteredCompensation.getSalary() == null) {
            throw new RuntimeException("Salary is not provided/ is null");
        }
        if(enteredCompensation.getEffectiveDate()==null) {
            throw new RuntimeException("Effective Date is not provided/ is null");
        }


    }

}