package com.staywell.document;

import com.staywell.dto.response.RentCarResponse;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CarRentTicket {
    @Id
    private String carRentTicketID;

    RentCarResponse rentCarResponse;

}
