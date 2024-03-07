package com.staywell.repository.mongo;

import com.staywell.document.CarRentTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRentTicketRepository extends MongoRepository<CarRentTicket, String> {
}
