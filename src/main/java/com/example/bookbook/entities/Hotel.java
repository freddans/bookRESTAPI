package com.example.bookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;
    @Column(name="city")
    private String city;
    @Column(name="wifi")
    private boolean wifi;
    @Column(name="stars")
    private int stars;
    @Column(name="price")
    private double price;
    @Column(name = "booked")
    private boolean isBooked;

    public Hotel() {
    }

    public Hotel(String name, String country, String city, boolean wifi, int stars, double price) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.wifi = wifi;
        this.stars = stars;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean hasWiFi) {
        this.wifi = hasWiFi;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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