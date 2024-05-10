package com.example.bookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transportation")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private double price;
    @Column(name = "booked")
    private boolean isBooked;

    public Transportation() {
    }

    public Transportation(String type, double price) {
        this.type = type;
        this.price = price;
        this.isBooked = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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