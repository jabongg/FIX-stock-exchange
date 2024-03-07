package com.staywell.service;

import com.staywell.dto.request.FlightRequest;
import com.staywell.dto.response.FlightResponse;
import org.springframework.stereotype.Service;

@Service
public interface FlightService {
    public FlightResponse bookFlight(FlightRequest flightRequest);
}
