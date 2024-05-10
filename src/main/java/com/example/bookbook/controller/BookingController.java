package com.example.bookbook.controller;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Transportation;
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
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/add")
    public Booking create(@RequestParam("flightId") long flightId,
                          @RequestParam("hotelId") long hotelId,
                          @RequestParam("transportationId") long transportationId) {
        return bookingService.create(flightId, hotelId, transportationId);
    }

    @GetMapping("/{id}")
    public Booking findBookingById(@PathVariable long id) {
        return bookingService.findBookingById(id);
    }

    @PutMapping("/update/{id}")
    public Booking updateBooking(@PathVariable long id, @RequestParam("flightId") long flightId, @RequestParam("hotelId") long hotelId, @RequestParam("transportationId") long transportationId) {
        return bookingService.updateBooking(id, flightId, hotelId, transportationId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        return bookingService.deleteBooking(id);
    }
}
