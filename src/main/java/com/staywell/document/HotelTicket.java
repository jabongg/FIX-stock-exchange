package com.staywell.document;

import com.staywell.dto.response.HotelResponse;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class HotelTicket {

    @Id
    private String hotelTicketID;
    private HotelResponse hotelResponse;
}
