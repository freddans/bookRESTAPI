package com.example.bookbook.controller;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/availableorders")
    public List<Booking> allAvailableOrders() {
        return userService.getAllAvailableOrders();
    }

    @GetMapping("/availableflights")
    public List<Flight> allAvailableFlights() {
        return userService.getAllAvailableFlights();
    }

    @GetMapping("/availablehotels")
    public List<Hotel> allAvailableHotels() {
        return userService.getAllAvailableHotels();
    }
}
