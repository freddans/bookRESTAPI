package com.example.bookbook.entities;

import com.example.bookbook.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "travelpackage_id")
    private TravelPackage travelPackage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date bookingDate;

    @Column(name = "canceled")
    private Boolean isCanceled;

    public Booking() {
    }

    public Booking(TravelPackage travelPackage, User user) {
        this.travelPackage = travelPackage;
        this.user = user;
        this.bookingDate = new Date();
        this.isCanceled = false;
    }

    public Booking(TravelPackage travelPackage, User user, Date bookingDate) {
        this.travelPackage = travelPackage;
        this.user = user;
        this.bookingDate = bookingDate;
        this.isCanceled = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }
}
