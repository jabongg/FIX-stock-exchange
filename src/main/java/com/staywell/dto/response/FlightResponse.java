package com.staywell.dto.response;

import com.staywell.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResponse {
    String fligthNumber;

    String flightName;

    String bookingDate;

    Customer customer;
}
