package com.example.bookbook.controller;

import com.example.bookbook.entities.EventBooking;
import com.example.bookbook.service.EventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventbooking")
public class EventBookingController {

    private EventBookingService eventBookingService;

    @Autowired
    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EventBooking> allBookedEvents() {
        return eventBookingService.getAllBookedEvents();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@RequestParam("userId") long userId, @RequestParam("eventId") long eventId) {
        return eventBookingService.createEventBookingAdmin(userId, eventId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EventBooking getEventBookingById(@PathVariable long id) {
        return eventBookingService.findBookedEventById(id);
    }

    @PutMapping("/cancelorder/")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancel(@RequestParam("userId") long userId, @RequestParam("eventBookingId") long eventBookingId) {
        return eventBookingService.cancel(userId, eventBookingId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable long id) {
        return eventBookingService.delete(id);
    }
}
