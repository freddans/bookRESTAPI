package com.example.bookbook.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "flight_home_id", nullable = false)
    private Flight flightHome;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transportation_home_id", nullable = false)
    private Transportation transportationHome;

    public TravelPackage() {
    }

    public TravelPackage(Flight flight, Hotel hotel, Transportation transportation) {
        this.flight = flight;
        this.hotel = hotel;
        this.transportation = transportation;
    }

    public TravelPackage(Flight flight, Hotel hotel, Transportation transportation, Flight flightHome, Transportation transportationHome) {
        this.flight = flight;
        this.hotel = hotel;
        this.transportation = transportation;
        this.flightHome = flightHome;
        this.transportationHome = transportationHome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public Flight getFlightHome() {
        return flightHome;
    }

    public void setFlightHome(Flight flightHome) {
        this.flightHome = flightHome;
    }

    public Transportation getTransportationHome() {
        return transportationHome;
    }

    public void setTransportationHome(Transportation transportationHome) {
        this.transportationHome = transportationHome;
    }
}
