package com.example.bookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airLine;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;

    public Booking() {
    }

    public Booking(Airline airLine, Hotel hotel, Transportation transportation) {
        this.airLine = airLine;
        this.hotel = hotel;
        this.transportation = transportation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airline getAirLine() {
        return airLine;
    }

    public void setAirLine(Airline airLine) {
        this.airLine = airLine;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
}
