package com.example.bookbook.service;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.TravelPackage;
import com.example.bookbook.repositories.BookingRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private AdminService adminService;
    private TravelPackageService travelPackageService;
    private FlightService flightService;
    private HotelService hotelService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, AdminService adminService, TravelPackageService travelPackageService, FlightService flightService, HotelService hotelService) {
        this.bookingRepository = bookingRepository;
        this.adminService = adminService;
        this.travelPackageService = travelPackageService;
        this.flightService = flightService;
        this.hotelService = hotelService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String create(long userId, long travelPackageId) {
        User user = adminService.findUserById(userId);
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(travelPackageId);

        if (user != null) {

            if (travelPackage != null) {

                Booking booking = new Booking(travelPackage, user);

                booking.getTravelPackage().getFlight().setAvailableSeats(booking.getTravelPackage().getFlight().getAvailableSeats() -1);
                booking.getTravelPackage().getHotel().setAvailableRooms(booking.getTravelPackage().getHotel().getAvailableRooms() -1);

                user.addBooking(booking);

                bookingRepository.save(booking);

                String country = booking.getTravelPackage().getHotel().getCountry();
                String city = booking.getTravelPackage().getHotel().getCity();

                return user.getName() + " booked a trip to " + city + ", " + country;

            } else {

                return "provided Travelpackage ID not found";
            }

        } else {

            return "provided User ID not found";
        }
    }

    public Booking findBookingById(long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {

            Booking booking = optionalBooking.get();

            return booking;
        } else {

            return null;
        }
    }

    public String cancel(long userId, long bookingId) {
        User user = adminService.findUserById(userId);
        Booking booking = findBookingById(bookingId);

        if (user != null) {

            if (booking != null) {

                if (!booking.getCanceled()) {

                    user.removeBooking(booking);

                    Flight flight = booking.getTravelPackage().getFlight();
                    Hotel hotel = booking.getTravelPackage().getHotel();

                    String country = hotel.getCountry();
                    String city = hotel.getCity();

                    flight.setAvailableSeats(flight.getAvailableSeats() +1);
                    hotel.setAvailableRooms(hotel.getAvailableRooms() +1);

                    booking.setCanceled(true);

                    flightService.save(flight);
                    hotelService.save(hotel);

                    bookingRepository.save(booking);
                    adminService.save(user);

                    return user.getName() + " canceled their trip to " + city + ", " + country;
                } else {

                    return "Booking has been canceled";
                }


            } else {

                return "provided Travelpackage ID not found";
            }

        } else {

            return "provided User ID not found";
        }
    }

    public String delete(long id) {
        Booking bookingToDelete = findBookingById(id);

        if (bookingToDelete != null) {

            User user = bookingToDelete.getUser();

            user.removeBooking(bookingToDelete);

            bookingRepository.delete(bookingToDelete);

            return "Deleted booking";
        } else {

            return "provided booking ID does not exist";
        }
    }
}
