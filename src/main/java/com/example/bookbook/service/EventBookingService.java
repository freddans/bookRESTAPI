package com.example.bookbook.service;

import com.example.bookbook.entities.*;
import com.example.bookbook.repositories.EventBookingRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventBookingService {

    private EventBookingRepository eventBookingRepository;
    private AdminService adminService;
    private EventService eventService;

    @Autowired
    public EventBookingService(EventBookingRepository eventBookingRepository, AdminService adminService, EventService eventService) {
        this.eventBookingRepository = eventBookingRepository;
        this.adminService = adminService;
        this.eventService = eventService;
    }

    public List<EventBooking> getAllBookedEvents() {
        return eventBookingRepository.findAll();
    }

    public String createEventBookingAdmin(long userId, long eventId) {
        User user = adminService.findUserById(userId);
        Event event = eventService.findEventById(eventId);

        if (user != null) {

            if (event != null) {

                // Create booking
                EventBooking eventBooking = new EventBooking(event, user);

                // Occupy a ticket
                Event theEvent = eventBooking.getEvent();

                theEvent.setTickets(theEvent.getTickets() - 1);

                // Add to User eventList
                user.addEvent(eventBooking);

                // Save eventbooking
                eventBookingRepository.save(eventBooking);

                return "Admin booked a ticket for " + user.getName() + " to event " + theEvent.getName();

            } else {

                return "ERROR: provided Event ID not found";
            }

        } else {

            return "ERROR: provided User ID not found";
        }
    }

    public EventBooking findBookedEventById(long id) {
        Optional<EventBooking> optionalEventBooking = eventBookingRepository.findById(id);

        if (optionalEventBooking.isPresent()) {
            EventBooking eventBooking = optionalEventBooking.get();

            return eventBooking;
        } else {

            return null;
        }
    }

    public String cancel(long userId, long eventBookingId) {
        User user = adminService.findUserById(userId);
        EventBooking eventBooking = findBookedEventById(eventBookingId);

        if (user != null) {

            if (eventBooking != null) {

                if (eventBooking.getUser() == user) {

                    user.removeEvent(eventBooking);

                    if (!eventBooking.getCanceled()) {

                        Event theEvent = eventBooking.getEvent();

                        theEvent.setTickets(theEvent.getTickets() + 1);

                        eventBooking.setCanceled(true);

                        eventBookingRepository.save(eventBooking);

                        return user.getName() + " canceled their event for " + theEvent.getName();
                    } else {

                        return "ERROR: Booking is already canceled";
                    }
                } else {

                    return "ERROR: You are not part of this booking";
                }

            } else {

                return "ERROR: provided Event Booking ID not found";
            }

        } else {

            return "ERROR: provided User ID not found";
        }
    }

    public String delete(long id) {
        EventBooking eventBookingToDelete = findBookedEventById(id);

        if (eventBookingToDelete != null) {

            User user = eventBookingToDelete.getUser();

            user.removeEvent(eventBookingToDelete);

            eventBookingRepository.delete(eventBookingToDelete);

            return "Deleted booked event";
        } else {

            return "ERROR: provided eventbooking ID does not exist";
        }
    }

    public String createEventBooking(User user, Event event) {


        // Create booking
        EventBooking eventBooking = new EventBooking(event, user, new Date());

        // Take up a ticket
        event.setTickets(event.getTickets() - 1);

        // Add the booking into the Users bookingList
        user.addEvent(eventBooking);

        // Update/Save the booking
        eventBookingRepository.save(eventBooking);

        return user.getName() + " booked a ticket for event " + eventBooking.getEvent().getName();

    }

}
