package com.example.bookbook.controller;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public List<Booking> listAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/add")
    public String create(@RequestParam("userId") long userId, @RequestParam("travelPackageId") long travelPackageId) {
        return bookingService.create(userId, travelPackageId);
    }

    @GetMapping("/{id}")
    public Booking getEventById(@PathVariable long id) {
        return bookingService.findBookingById(id);
    }

    @PutMapping("/cancelorder")
    public String cancel(@RequestParam("userId") long userId, @RequestParam("bookingId") long bookingId) {
        return bookingService.cancel(userId, bookingId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return bookingService.delete(id);
    }
}
