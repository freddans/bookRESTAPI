package com.example.bookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure")
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "airline")
    private String airline;
    @Column(name = "price")
    private double price;

    @Column(name = "booked")
    boolean isBooked;

    public Flight() {
    }

    public Flight(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Flight(String name, String destination, String departure, String arrival, String airline, double price) {
        this.name = name;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
        this.price = price;
        this.isBooked = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
