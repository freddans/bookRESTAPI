package com.example.bookbook.service;

import com.example.bookbook.entities.Airline;
import com.example.bookbook.entities.Booking;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.Transportation;
import com.example.bookbook.repositories.AirlineRepository;
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
    private AirlineRepository airlineRepository;
    private HotelRepository hotelRepository;
    private TransportationRepository transportationRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, AirlineRepository airlineRepository, HotelRepository hotelRepository, TransportationRepository transportationRepository) {
        this.bookingRepository = bookingRepository;
        this.airlineRepository = airlineRepository;
        this.hotelRepository = hotelRepository;
        this.transportationRepository = transportationRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking create(long airlineId, long hotelId, long transportationId) {
        Optional<Airline> optionalAirline = airlineRepository.findById(airlineId);
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        Optional<Transportation> optionalTransportation = transportationRepository.findById(transportationId);

        if (optionalAirline.isPresent() && optionalHotel.isPresent() && optionalTransportation.isPresent()) {
            Airline airline = optionalAirline.get();
            Hotel hotel = optionalHotel.get();
            Transportation transportation = optionalTransportation.get();

            System.out.println("total price: " + (airline.getPrice() + hotel.getPrice() + transportation.getPrice()));

            Booking newBooking = new Booking(airline, hotel, transportation);

            bookingRepository.save(newBooking);

            return newBooking;
        } else {

            return null;
        }
    }
}
