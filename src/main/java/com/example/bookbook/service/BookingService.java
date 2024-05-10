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
    private FlightRepository flightRepository;
    private HotelRepository hotelRepository;
    private TransportationRepository transportationRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository, HotelRepository hotelRepository, TransportationRepository transportationRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.hotelRepository = hotelRepository;
        this.transportationRepository = transportationRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking create(long flightId, long hotelId, long transportationId) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        Optional<Transportation> optionalTransportation = transportationRepository.findById(transportationId);

        if (optionalFlight.isPresent() && optionalHotel.isPresent() && optionalTransportation.isPresent()) {
            Flight flight = optionalFlight.get();
            Hotel hotel = optionalHotel.get();
            Transportation transportation = optionalTransportation.get();

            System.out.println("total price: " + (flight.getPrice() + hotel.getPrice() + transportation.getPrice()));

            Booking newBooking = new Booking(flight, hotel, transportation);

            bookingRepository.save(newBooking);

            return newBooking;
        } else {

            return null;
        }
    }
}
