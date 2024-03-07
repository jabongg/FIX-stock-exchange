package com.staywell.repository.mongo;

import com.staywell.document.FlightTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightTicketRepository extends MongoRepository<FlightTicket, String> {
}
