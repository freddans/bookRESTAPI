package com.example.bookbook.controller;

import com.example.bookbook.entities.Event;
import com.example.bookbook.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/add")
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable long id) {
        return eventService.findEventById(id);
    }

    @PutMapping("/update/{id}")
    public Event update(@PathVariable long id, @RequestBody Event event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return eventService.delete(id);
    }
}
