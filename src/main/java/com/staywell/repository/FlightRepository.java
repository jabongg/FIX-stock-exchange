package com.staywell.repository;

import com.staywell.dto.request.FlightRequest;
import com.staywell.dto.response.FlightResponse;
import com.staywell.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    public FlightResponse save(FlightRequest flightRequest);
}
