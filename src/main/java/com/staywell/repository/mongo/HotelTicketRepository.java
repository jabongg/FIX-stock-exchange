package com.staywell.repository.mongo;

import com.staywell.document.HotelTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelTicketRepository extends MongoRepository<HotelTicket, String> {
}
