package com.staywell.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.staywell.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightRequest {

    private String flightName;

    private Customer customer;
}
