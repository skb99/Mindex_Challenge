package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
// Creating the Reporting Structure
    @Override
    public ReportingStructure createReportingStructure(String employeeId) throws RuntimeException {
        LOG.debug("Creating report for employee with id: ", employeeId);

        //Get the base Employee i.e Base of the Tree
        Employee baseEmployee = employeeRepository.findByEmployeeId(employeeId);
        if (baseEmployee == null){
            throw new RuntimeException("Provided Employee not found with employeeId: " + employeeId);
        }
        //Instantiate the reportingTree object for the Reporting structure
        ReportingStructure reportingTree = new ReportingStructure();

        //set the Base of the tree
        reportingTree.setEmployee(baseEmployee);

        //use the helper function below to populate the rest of the created reporting tree
        populateDistinctReports(baseEmployee, reportingTree);

        return reportingTree;
    }

    private void populateDistinctReports(Employee baseEmployee, ReportingStructure reportingTree) {
        // If base Employee and direct Reporters exist

        if (baseEmployee.getDirectReports() != null) {
           List<Employee> directEmployeeList = baseEmployee.getDirectReports();
            // List of distinct Reporters
            List<Employee> distinctReports = new ArrayList<>();

            for(Employee directEmployee : directEmployeeList ) {
                // Get name, department etc. info for each directEmployee
                Employee reportingEmployee = employeeRepository.findByEmployeeId(directEmployee.getEmployeeId());

                distinctReports.add(reportingEmployee);
                // reset the new base of the Tree
                baseEmployee.setDirectReports(distinctReports);
                // add to the count for each directEmployee
                reportingTree.setNumberOfReports(reportingTree.getNumberOfReports() + 1);
                // Call the method again to keep creating until DirectReports is null
                populateDistinctReports(reportingEmployee, reportingTree);
            }
        }
    }

}
