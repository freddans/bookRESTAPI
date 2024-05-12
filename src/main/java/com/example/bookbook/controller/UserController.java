package com.example.bookbook.controller;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addtouserid/{id}")
    public String create(@PathVariable long id, @RequestParam("bookingId") long bookingId) {
        return userService.create(id, bookingId);
    }

    @PutMapping("/cancelorderforuserid/{id}")
    public String cancel(@PathVariable long id, @RequestParam("bookingId") long bookingId) {
        return userService.cancel(id, bookingId);
    }

    @GetMapping("/myorders/{id}")
    public List<Booking> myOrders(@PathVariable long id) {
        return userService.getMyOrders(id);
    }
}
