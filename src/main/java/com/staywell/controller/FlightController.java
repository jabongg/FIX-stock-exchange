package com.staywell.controller;

import com.staywell.dto.request.FlightRequest;
import com.staywell.dto.response.FlightResponse;
import com.staywell.service.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staywell/flights")
@AllArgsConstructor
public class FlightController {

    @Autowired
    FlightService flightService;
    @PostMapping("/book-flight")
    public ResponseEntity<FlightResponse> bookFlight(@Valid @RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.bookFlight(flightRequest), HttpStatus.CREATED);
    }
}
