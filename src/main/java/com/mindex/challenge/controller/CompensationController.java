package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;
    // Endpoint to create new comensation
    @PostMapping("/compensation/create")
    public Compensation createComp(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        return compensationService.createComp(compensation);
    }
    // Endpoint to get/read existing compensation
    @GetMapping("/compensation/{id}")
    public Compensation readComp(@PathVariable String id) {
        LOG.debug("Received compensation read request for [{}]", id);
        return compensationService.readComp(id);
    }
}