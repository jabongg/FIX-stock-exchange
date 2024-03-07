package com.staywell.service.impl;

import com.staywell.dto.request.FlightRequest;
import com.staywell.dto.response.FlightResponse;
import com.staywell.repository.FlightRepository;
import com.staywell.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepo;

    @Override
    public FlightResponse bookFlight(FlightRequest flightRequest) {
        return flightRepo.save(flightRequest);
    }
}
