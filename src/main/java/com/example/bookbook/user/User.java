package com.example.bookbook.user;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Event;
import com.example.bookbook.entities.TravelPackage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "hasbookings")
    private boolean activeBookings;

    @JsonIgnore
    @ManyToMany
    List<Booking> bookingList;

    @JsonIgnore
    @ManyToMany
    List<Event> eventList;


    public User() {
    }

    public User(String name, String birthday, String address, String phone, String email) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.activeBookings = false;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasActiveBookings() {
        return activeBookings;
    }

    public void setActiveBookings(boolean activeBookings) {
        this.activeBookings = activeBookings;
    }

    public boolean isActiveBookings() {
        return activeBookings;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookingList.remove(booking);
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void removeEvent(Event event) {
        eventList.remove(event);
    }
}
