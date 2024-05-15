package com.example.bookbook.service;

import com.example.bookbook.entities.*;
import com.example.bookbook.repositories.EventRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event create(Event event) {

        Event newEvent = new Event(event.getName(), event.getPrice(), 50);

        eventRepository.save(newEvent);

        return newEvent;
    }

    public Event findEventById(long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isPresent()) {

            Event event = optionalEvent.get();

            return event;
        } else {

            return null;
        }
    }

    public Event update(long id, Event newEventInformation) {
        Event existingEvent = findEventById(id);

        if (existingEvent != null) {

            if (!newEventInformation.getName().contains(existingEvent.getName()) && newEventInformation.getName() != null) {

                existingEvent.setName(newEventInformation.getName());
            }
            if (newEventInformation.getPrice() != existingEvent.getPrice() && newEventInformation.getPrice() != 0) {

                existingEvent.setPrice(newEventInformation.getPrice());
            }

            eventRepository.save(existingEvent);

            return existingEvent;
        } else {

            return null;
        }
    }

    public String delete(long id) {
        Event eventToDelete = findEventById(id);

        if (eventToDelete != null) {

            eventRepository.delete(eventToDelete);

            return "Event deleted";
        } else {

            return "provided event ID does not exist";
        }
    }


}
