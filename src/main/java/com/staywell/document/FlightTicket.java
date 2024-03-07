package com.staywell.document;

import com.staywell.dto.response.FlightResponse;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FlightTicket {
    @Id
    private String flightTicketID;

    private FlightResponse flightResponse;
}
