package com.staywell.java;

import java.util.*;

public class FlightsByMinimumPrice {

    public static void main(String[] args) {
        // Sample flights
        List<Flight> flights = Arrays.asList(
                new Flight("A", "B", 100, 0),
                new Flight("A", "B", 150, 1),
                new Flight("A", "B", 200, 2),
                new Flight("A", "B", 120, 0),
                new Flight("A", "C", 180, 0)
        );

        String source = "A";
        String destination = "B";
        int maxStops = 1;

        // Filter flights based on source and destination
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.source.equals(source) && flight.destination.equals(destination) && flight.stops <= maxStops) {
                filteredFlights.add(flight);
            }
        }

        // Sort filtered flights by price
        Collections.sort(filteredFlights, Comparator.comparingInt(f -> f.price));

        // Print sorted flights
        for (Flight flight : filteredFlights) {
            System.out.println(flight);
        }
    }
}
