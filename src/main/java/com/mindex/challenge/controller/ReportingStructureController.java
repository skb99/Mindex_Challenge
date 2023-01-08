package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingService;
    // Endpoint to get Reporting Structure for an employee and the number of reporters
    @GetMapping("/reporting/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id) {
        LOG.debug("Received create-report request for employee with id [{}]", id);
        return reportingService.createReportingStructure(id);
    }
}