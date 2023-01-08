package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingUrl;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingUrl = "http://localhost:" + port + "/reporting/{id}";
    }

    @Test
    public void testReportingCounterJohn() {
        String targetEmployeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure report = restTemplate.getForEntity(reportingUrl, ReportingStructure.class, targetEmployeeID).getBody();
        Assertions.assertEquals(4,report.getNumberOfReports());
    }
    @Test
    public void testReportingCounterPaul() {
        String targetEmployeeID = "b7839309-3348-463b-a7e3-5de1c168beb3";
        ReportingStructure report = restTemplate.getForEntity(reportingUrl, ReportingStructure.class, targetEmployeeID).getBody();
        Assertions.assertEquals(0,report.getNumberOfReports());
    }

}