package com.example.bookbook.service;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.Transportation;
import com.example.bookbook.repositories.FlightRepository;
import com.example.bookbook.repositories.BookingRepository;
import com.example.bookbook.repositories.HotelRepository;
import com.example.bookbook.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private FlightService flightService;
    private HotelService hotelService;
    private TransportationService transportationService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, FlightService flightService, HotelService hotelService, TransportationService transportationService) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.transportationService = transportationService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking create(long flightId, long hotelId, long transportationId) {
        Flight flight = flightService.findFlightById(flightId);
        Hotel hotel = hotelService.findHotelById(hotelId);
        Transportation transportation = transportationService.findTransportationById(transportationId);

        if (flight != null && hotel != null && transportation != null) {
            System.out.println("total price: " + (flight.getPrice() + hotel.getPrice() + transportation.getPrice()));

            Booking newBooking = new Booking(flight, hotel, transportation);

            bookingRepository.save(newBooking);

            return newBooking;
        } else {

            return null;
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

    public Booking updateBooking(long id, long flightId, long hotelId, long transportationId) {
        Booking existingBooking = findBookingById(id);

        if (existingBooking != null) {

            if (flightId != existingBooking.getFlight().getId() && flightId != 0) {

                Flight newFlight = flightService.findFlightById(flightId);

                existingBooking.setFlight(newFlight);
            }
            if (hotelId != existingBooking.getHotel().getId() && hotelId != 0) {

                Hotel newHotel = hotelService.findHotelById(hotelId);

                existingBooking.setHotel(newHotel);
            }
            if (transportationId != existingBooking.getTransportation().getId() && transportationId != 0) {

                Transportation newTransportation = transportationService.findTransportationById(transportationId);

                existingBooking.setTransportation(newTransportation);
            }

            bookingRepository.save(existingBooking);
        }

        return existingBooking;
    }

    public String deleteBooking(long id) {
        Booking bookingToDelete = findBookingById(id);

        if (bookingToDelete != null) {

            Flight flight = bookingToDelete.getFlight();
            flight.setBooked(false);

            Hotel hotel = bookingToDelete.getHotel();
            hotel.setBooked(false);

            Transportation transportation = bookingToDelete.getTransportation();
            transportation.setBooked(false);

            bookingRepository.delete(bookingToDelete);
        }

        return "Deleted booking";
    }
}
