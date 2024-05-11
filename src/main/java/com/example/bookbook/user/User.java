package com.example.bookbook.user;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private boolean activeBookings;


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
}
