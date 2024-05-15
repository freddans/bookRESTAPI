package com.example.bookbook.entities;

import com.example.bookbook.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "eventbookings")
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private Date bookingDate;

    @Column(name = "canceled")
    private Boolean isCanceled;

    public EventBooking() {
    }

    public EventBooking(Event event, User user) {
        this.event = event;
        this.user = user;
        this.bookingDate = new Date();
        this.isCanceled = false;
    }

    public EventBooking(Event event, User user, Date bookingDate) {
        this.event = event;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
