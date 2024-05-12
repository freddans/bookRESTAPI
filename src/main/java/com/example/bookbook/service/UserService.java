package com.example.bookbook.service;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookingService bookingService;
    private FlightService flightService;
    private HotelService hotelService;

    @Autowired
    public UserService(UserRepository userRepository, BookingService bookingService, FlightService flightService, HotelService hotelService) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
        this.flightService = flightService;
        this.hotelService = hotelService;
    }

    public List<Booking> getAllAvailableOrders() {
        List<Booking> allAvailableBookings = new ArrayList<>();

        for (Booking booking : bookingService.getAllBookings()) {
            if (booking.getFlight().getAvailableSeats() != 0 && booking.getHotel().getAvailableRooms() != 0) {

                allAvailableBookings.add(booking);
            }

        }

        return allAvailableBookings;
    }

    public List<Flight> getAllAvailableFlights() {
        List<Flight> allAvailableFlights = new ArrayList<>();

        for (Flight flight : flightService.getAllFlights()) {

            if (flight.getAvailableSeats() != 0) {

                allAvailableFlights.add(flight);
            }
        }

        return allAvailableFlights;
    }

    public List<Hotel> getAllAvailableHotels() {
        List<Hotel> allAvailableHotels = new ArrayList<>();

        for (Hotel hotel : hotelService.getAllHotels()) {

            if (hotel.getAvailableRooms() != 0) {

                allAvailableHotels.add(hotel);
            }
        }

        return allAvailableHotels;
    }
}
