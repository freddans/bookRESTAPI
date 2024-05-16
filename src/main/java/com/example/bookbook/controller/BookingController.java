package com.example.bookbook.controller;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> listAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@RequestParam("userId") long userId, @RequestParam("travelPackageId") long travelPackageId) {
        return bookingService.createBookingAdmin(userId, travelPackageId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Booking getBookingById(@PathVariable long id) {
        return bookingService.findBookingById(id);
    }

    @PutMapping("/cancelorder")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancel(@RequestParam("userId") long userId, @RequestParam("bookingId") long bookingId) {
        return bookingService.cancel(userId, bookingId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable long id) {
        return bookingService.delete(id);
    }
}
