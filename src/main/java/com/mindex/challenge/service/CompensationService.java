package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation createComp(Compensation employee);
    Compensation readComp(String employeeId);
}