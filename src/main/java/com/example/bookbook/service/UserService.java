package com.example.bookbook.service;

import com.example.bookbook.entities.*;
import com.example.bookbook.repositories.UserRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private AdminService adminService;
    private TravelPackageService travelPackageService;
    private FlightService flightService;
    private HotelService hotelService;
    private BookingService bookingService;
    private EventService eventService;
    private EventBookingService eventBookingService;

    @Autowired
    public UserService(UserRepository userRepository, TravelPackageService travelPackageService, FlightService flightService, HotelService hotelService, AdminService adminService, BookingService bookingService, EventService eventService, EventBookingService eventBookingService) {
        this.userRepository = userRepository;
        this.travelPackageService = travelPackageService;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.adminService = adminService;
        this.bookingService = bookingService;
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
    }

    public List<TravelPackage> getAllAvailableTravelPackages() {
        List<TravelPackage> allAvailableTravelPackages = new ArrayList<>();

        for (TravelPackage travelPackage : travelPackageService.getAllTravelPackages()) {
            if (travelPackage.getFlight().getAvailableSeats() != 0 && travelPackage.getHotel().getAvailableRooms() != 0) {

                allAvailableTravelPackages.add(travelPackage);
            }

        }

        return allAvailableTravelPackages;
    }

    public List<Flight> getAllAvailableFlights() {
        List<Flight> allAvailableFlights = new ArrayList<>();

        for (Flight flight : flightService.getAllFlights()) {

            if (flight.getAvailableSeats() != 0 && flight.isPackaged()) {

                allAvailableFlights.add(flight);
            }
        }

        return allAvailableFlights;
    }

    public List<Hotel> getAllAvailableHotels() {
        List<Hotel> allAvailableHotels = new ArrayList<>();

        for (Hotel hotel : hotelService.getAllHotels()) {

            if (hotel.getAvailableRooms() != 0 && hotel.isPackaged()) {

                allAvailableHotels.add(hotel);
            }
        }

        return allAvailableHotels;
    }

//    public String create(long userId, long bookingId) {
//        User user = adminService.findUserById(userId);
//        TravelPackage travelPackage = travelPackageService.findTravelPackageById(bookingId);
//
//        if (user != null) {
//
//            if (travelPackage != null) {
//
//                if (travelPackage.getFlight().getAvailableSeats() != 0 && travelPackage.getHotel().getAvailableRooms() != 0) {
//
//                    user.addBooking(travelPackage);
//
//                    travelPackage.getFlight().setAvailableSeats(travelPackage.getFlight().getAvailableSeats() -1);
//                    travelPackage.getHotel().setAvailableRooms(travelPackage.getHotel().getAvailableRooms() -1);
//
//                    travelPackageService.updateTravelPackage(travelPackage.getId(), travelPackage.getFlight().getId(), travelPackage.getHotel().getId(), travelPackage.getTransportation().getId());
//                    userRepository.save(user);
//
//                    return "User " + user.getName() + " successfully booked a trip to: " + travelPackage.getHotel().getCountry() + ", " + travelPackage.getHotel().getCity();
//                } else {
//
//                    return "There are no available seats or rooms";
//                }
//            } else {
//
//                return "booking Id provided does not exist";
//            }
//
//        } else {
//
//            return "user Id provided does not exist";
//        }
//    }


    public String createBooking(long userId, long travelPackageId) {
        User user = adminService.findUserById(userId);
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(travelPackageId);

        if (user == null) {

            return "ERROR: Provided User ID not found";
        }

        if (travelPackage == null) {

            return "ERROR: Provided Travelpackage ID not found";
        }

        return bookingService.createBooking(user, travelPackage);
    }

    public String createEventBooking(long userId, long eventId) {
        User user = adminService.findUserById(userId);
        Event event = eventService.findEventById(eventId);

        if (user == null) {

            return "ERROR: Provided User ID not found";
        }

        if (event == null) {

            return "ERROR: Provided Event ID not found";
        }

        return eventBookingService.createEventBooking(user, event);
    }

    public String cancel(long userId, long bookingId) {

        return bookingService.cancel(userId, bookingId);
    }

    public String cancelEventBooking(long userId, long eventBookingId) {

        return eventBookingService.cancel(userId, eventBookingId);
    }

    public List<Booking> getMyOrders(long id) {
        User user = adminService.findUserById(id);

        if (user != null) {

            return user.getBookingList();
        } else {

            return null;
        }
    }

    public List<EventBooking> getMyEventOrders(long id) {
        User user = adminService.findUserById(id);

        if (user != null) {

            return user.getEventList();
        } else {

            return null;
        }
    }
}
