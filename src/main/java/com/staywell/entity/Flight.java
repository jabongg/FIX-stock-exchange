package com.staywell.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String flightId;

    String flightName;

    private LocalDate boardingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
