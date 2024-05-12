package com.example.bookbook.service;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.repositories.UserRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private AdminService adminService;
    private BookingService bookingService;
    private FlightService flightService;
    private HotelService hotelService;

    @Autowired
    public UserService(UserRepository userRepository, BookingService bookingService, FlightService flightService, HotelService hotelService, AdminService adminService) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.adminService = adminService;
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

    public String create(long userId, long bookingId) {
        User user = adminService.findUserById(userId);
        Booking booking = bookingService.findBookingById(bookingId);

        if (user != null) {

            if (booking != null) {

                if (booking.getFlight().getAvailableSeats() != 0 && booking.getHotel().getAvailableRooms() != 0) {

                    user.addBooking(booking);

                    booking.getFlight().setAvailableSeats(booking.getFlight().getAvailableSeats() -1);
                    booking.getHotel().setAvailableRooms(booking.getHotel().getAvailableRooms() -1);

                    bookingService.updateBooking(booking.getId(), booking.getFlight().getId(), booking.getHotel().getId(), booking.getTransportation().getId());
                    userRepository.save(user);

                    return "User " + user.getName() + " successfully booked a trip to: " + booking.getHotel().getCountry() + ", " + booking.getHotel().getCity();
                } else {

                    return "There are no available seats or rooms";
                }
            } else {

                return "booking Id provided does not exist";
            }

        } else {

            return "user Id provided does not exist";
        }
    }

    public String cancel(long userId, long bookingId) {
        User user = adminService.findUserById(userId);
        Booking booking = bookingService.findBookingById(bookingId);

        if (user != null) {

            if (booking != null) {

                user.removeBooking(booking);

                booking.getFlight().setAvailableSeats(booking.getFlight().getAvailableSeats() +1);
                booking.getHotel().setAvailableRooms(booking.getHotel().getAvailableRooms() +1);

                bookingService.updateBooking(booking.getId(), booking.getFlight().getId(), booking.getHotel().getId(), booking.getTransportation().getId());
                userRepository.save(user);

                return "User " + user.getName() + " successfully canceled their trip  to: " + booking.getHotel().getCountry() + ", " + booking.getHotel().getCity();
            } else {

                return "booking Id provided does not exist";
            }

        } else {

            return "user Id provided does not exist";
        }
    }

    public List<Booking> getMyOrders(long id) {
        User user = adminService.findUserById(id);

        if (user != null) {

            return user.getBookingList();
        } else {

            return null;
        }
    }
}
