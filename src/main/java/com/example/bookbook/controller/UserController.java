package com.example.bookbook.controller;

import com.example.bookbook.entities.*;
import com.example.bookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/availabletravelpackages")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<TravelPackage> allAvailableTravelPackages() {
        return userService.getAllAvailableTravelPackages();
    }

    @GetMapping("/availableflights")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Flight> allAvailableFlights() {
        return userService.getAllAvailableFlights();
    }

    @GetMapping("/availablehotels")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Hotel> allAvailableHotels() {
        return userService.getAllAvailableHotels();
    }

    @PostMapping("/addtouserid/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String createBooking(@PathVariable long id, @RequestParam("travelPackageId") long travelPackageId) {
        return userService.createBooking(id, travelPackageId);
    }

    @PostMapping("/addeventtouserid/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String createEventBooking(@PathVariable long id, @RequestParam("eventId") long eventId) {
        return userService.createEventBooking(id, eventId);
    }

    @PutMapping("/cancelorderforuserid/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String cancel(@PathVariable long id, @RequestParam("bookingId") long bookingId) {
        return userService.cancel(id, bookingId);
    }

    @PutMapping("/canceleventorderforuserid/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String cancelEventOrder(@PathVariable long id, @RequestParam("eventBookingId") long eventBookingId) {
        return userService.cancelEventBooking(id, eventBookingId);
    }

    @GetMapping("/myorders/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Booking> myOrders(@PathVariable long id) {
        return userService.getMyOrders(id);
    }

    @GetMapping("/myeventorders/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<EventBooking> myEventOrders(@PathVariable long id) {
        return userService.getMyEventOrders(id);
    }
}
