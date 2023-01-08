package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.validator.CompensationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationValidator compensationValidator;
    @Autowired
    private CompensationRepository compensationRepository;

    // Method to Create Compensation for existing employee
    @Override
    public Compensation createComp(Compensation comp) {
        LOG.debug("Creating compensation");
        // Validation for compensation data
        compensationValidator.validateCompensation(comp);

        Employee targetEmployee = employeeRepository.findByEmployeeId(comp.getEmployee().getEmployeeId());

        if (targetEmployee == null) {
            throw new RuntimeException("Employee not found with employeeId:" + comp.getEmployee().getEmployeeId());
        }
        comp.setEmployee(targetEmployee);
        compensationRepository.insert(comp);
        return comp;
    }

    // Method to Read the added Compensation for existing employee

    @Override
    public Compensation readComp(String employeeId) {
        LOG.debug("Retrieving Compensation Info on employeeId", employeeId);
        Compensation comp = compensationRepository.findCompensationByEmployeeEmployeeId(employeeId);
        if (comp == null) {
            throw new RuntimeException("No Compensation information is found for employeeId: " + employeeId);
        }
        return comp;
    }

}