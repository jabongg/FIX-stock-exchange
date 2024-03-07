package com.staywell.java;

public class Flight  {
    String source;
    String destination;
    int price;
    int stops;

    public Flight(String source, String destination, int price, int stops) {
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", stops=" + stops +
                '}';
    }
}
