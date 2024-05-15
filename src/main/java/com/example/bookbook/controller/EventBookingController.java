package com.example.bookbook.controller;

import com.example.bookbook.entities.EventBooking;
import com.example.bookbook.service.EventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EventBooking> allBookedEvents() {
        return eventBookingService.getAllBookedEvents();
    }

    @PostMapping("/add")
    public String create(@RequestParam("userId") long userId, @RequestParam("eventId") long eventId) {
        return eventBookingService.createEventBookingAdmin(userId, eventId);
    }

    @GetMapping("/{id}")
    public EventBooking getEventBookingById(@PathVariable long id) {
        return eventBookingService.findBookedEventById(id);
    }

    @PutMapping("/cancelorder/")
    public String cancel(@RequestParam("userId") long userId, @RequestParam("eventBookingId") long eventBookingId) {
        return eventBookingService.cancel(userId, eventBookingId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return eventBookingService.delete(id);
    }
}
