package com.example.bookbook.service;

import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.TravelPackage;
import com.example.bookbook.repositories.BookingRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public BookingService(BookingRepository bookingRepository, AdminService adminService, @Lazy TravelPackageService travelPackageService, FlightService flightService, HotelService hotelService) {
        this.bookingRepository = bookingRepository;
        this.adminService = adminService;
        this.travelPackageService = travelPackageService;
        this.flightService = flightService;
        this.hotelService = hotelService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Create booking in postman manually
    public String createBookingAdmin(long userId, long travelPackageId) {
        User user = adminService.findUserById(userId);
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(travelPackageId);

        if (user != null) {

            if (travelPackage != null) {

                // Create booking
                Booking booking = new Booking(travelPackage, user);

                // Get objects
                Flight flight = booking.getTravelPackage().getFlight();
                Hotel hotel = booking.getTravelPackage().getHotel();
                Flight flightHome = booking.getTravelPackage().getFlightHome();

                // Occupy seats and hotelroom
                flight.setAvailableSeats(flight.getAvailableSeats() - 1);
                hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
                flightHome.setAvailableSeats(flightHome.getAvailableSeats() - 1);

                // Add to User bookingList
                user.addBooking(booking);

                // Save booking
                bookingRepository.save(booking);


                // Info for Return message
                String country = booking.getTravelPackage().getHotel().getCountry();
                String city = booking.getTravelPackage().getHotel().getCity();

                return "Admin booked a trip for " + user.getName() + " to " + city + ", " + country;

            } else {

                return "ERROR: provided Travelpackage ID not found";
            }

        } else {

            return "ERROR: provided User ID not found";
        }
    }

    public String createBooking(User user, TravelPackage travelPackage) {


        // Create booking
        Booking booking = new Booking(travelPackage, user, new Date());

        // Get objects
        Flight flight = booking.getTravelPackage().getFlight();
        Hotel hotel = booking.getTravelPackage().getHotel();
        Flight flightHome = booking.getTravelPackage().getFlightHome();

        // Take up a seat and room
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        flightHome.setAvailableSeats(flightHome.getAvailableSeats() - 1);

        // Add the booking into the Users bookingList
        user.addBooking(booking);

        // Update/Save the booking
        bookingRepository.save(booking);

        // Info for Return message
        String country = booking.getTravelPackage().getHotel().getCountry();
        String city = booking.getTravelPackage().getHotel().getCity();

        return user.getName() + " booked a trip to " + city + ", " + country;

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

                if (booking.getUser() == user) {

                    user.removeBooking(booking);

                    if (!booking.getCanceled()) {

                        Flight flight = booking.getTravelPackage().getFlight();
                        Hotel hotel = booking.getTravelPackage().getHotel();
                        Flight flightHome = booking.getTravelPackage().getFlightHome();

                        String country = hotel.getCountry();
                        String city = hotel.getCity();

                        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
                        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
                        flightHome.setAvailableSeats(flightHome.getAvailableSeats() + 1);

                        booking.setCanceled(true);

                        flightService.save(flight);
                        hotelService.save(hotel);
                        flightService.save(flightHome);

                        bookingRepository.save(booking);

                        return user.getName() + " canceled their trip to " + city + ", " + country;
                    } else {

                        return "ERROR: Booking is already canceled";
                    }
                } else {

                    return "ERROR: You are not part of this booking";
                }




            } else {

                return "ERROR: provided Booking ID not found";
            }

        } else {

            return "ERROR: provided User ID not found";
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

            return "ERROR: provided booking ID does not exist";
        }
    }
}
