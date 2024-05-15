package com.example.bookbook.service;

import com.example.bookbook.entities.*;
import com.example.bookbook.repositories.TravelPackageRepository;
import com.example.bookbook.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageService {

    private TravelPackageRepository travelPackageRepository;
    private FlightService flightService;
    private HotelService hotelService;
    private TransportationService transportationService;
    private BookingService bookingService;

    @Autowired
    public TravelPackageService(TravelPackageRepository travelPackageRepository, FlightService flightService, HotelService hotelService, TransportationService transportationService, @Lazy BookingService bookingService) {
        this.travelPackageRepository = travelPackageRepository;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.transportationService = transportationService;
        this.bookingService = bookingService;
    }

    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll();
    }

//    public TravelPackage create(long flightId, long hotelId, long transportationId) {
//        Flight flight = flightService.findFlightById(flightId);
//        Hotel hotel = hotelService.findHotelById(hotelId);
//        Transportation transportation = transportationService.findTransportationById(transportationId);
//
//        if (flight != null && hotel != null && transportation != null) {
//
//            if (flight.getAvailableSeats() != 0) {
//
//                if (hotel.getAvailableRooms() != 0) {
//
//                    System.out.println("total price: " + (flight.getPrice() + hotel.getPrice() + transportation.getPrice()));
//
//                    TravelPackage newTravelPackage = new TravelPackage(flight, hotel, transportation);
//
//                    flight.setAvailableSeats(flight.getAvailableSeats() -1);
//                    hotel.setAvailableRooms(hotel.getAvailableRooms() -1);
//
//                    travelPackageRepository.save(newTravelPackage);
//
//                    return newTravelPackage;
//                }
//
//                // No hotel rooms available
//                return null;
//            } else {
//
//                // No seats available
//                return null;
//            }
//
//        } else {
//
//            return null;
//        }
//    }

    public TravelPackage create(long flightId, long hotelId, long transportationId) {
        Flight flight = flightService.findFlightById(flightId);
        Hotel hotel = hotelService.findHotelById(hotelId);
        Transportation transportation = transportationService.findTransportationById(transportationId);

        if (flight != null && hotel != null && transportation != null) {

            if (flight.getAvailableSeats() != 0) {

                if (hotel.getAvailableRooms() != 0) {

                    System.out.println("total price: " + (flight.getPrice() + hotel.getPrice() + transportation.getPrice()));

                    TravelPackage newTravelPackage = new TravelPackage(flight, hotel, transportation);

                    flight.setPackaged(true);
                    hotel.setPackaged(true);
                    transportation.setPackaged(true);

                    travelPackageRepository.save(newTravelPackage);

                    return newTravelPackage;
                }

                // No hotel rooms available - package cant be found
                return null;
            } else {

                // No seats available on plain - package cant be found
                return null;
            }

        } else {

            return null;
        }
    }

    public TravelPackage createWithFlightHome(long flightId, long hotelId, long transportationId, long flightHomeId, long transportationHomeId) {
        Flight flight = flightService.findFlightById(flightId);
        Hotel hotel = hotelService.findHotelById(hotelId);
        Transportation transportation = transportationService.findTransportationById(transportationId);

        Flight flightHome = flightService.findFlightById(flightHomeId);
        Transportation transportationHome = transportationService.findTransportationById(transportationHomeId);

        if (flight != null && hotel != null && transportation != null) {

            if (flight.getAvailableSeats() != 0) {

                if (hotel.getAvailableRooms() != 0) {

                    System.out.println("total price: " + (flight.getPrice() + hotel.getPrice() + transportation.getPrice() + flightHome.getPrice() + transportationHome.getPrice()));

                    TravelPackage newTravelPackage = new TravelPackage(flight, hotel, transportation, flightHome, transportationHome);

                    flight.setPackaged(true);
                    hotel.setPackaged(true);
                    transportation.setPackaged(true);
                    flightHome.setPackaged(true);
                    transportationHome.setPackaged(true);

                    travelPackageRepository.save(newTravelPackage);

                    return newTravelPackage;
                }

                // No hotel rooms available - package cant be found
                return null;
            } else {

                // No seats available on plain - package cant be found
                return null;
            }

        } else {

            return null;
        }
    }

    public TravelPackage findTravelPackageById(long id) {
        Optional<TravelPackage> optionalBooking = travelPackageRepository.findById(id);

        if (optionalBooking.isPresent()) {
            TravelPackage travelPackage = optionalBooking.get();

            return travelPackage;
        } else {

            return null;
        }
    }

    public TravelPackage updateTravelPackage(long id, long flightId, long hotelId, long transportationId, long flightHomeId, long transportationHomeId) {
        TravelPackage existingTravelPackage = findTravelPackageById(id);

        if (existingTravelPackage != null) {

            if (flightId != existingTravelPackage.getFlight().getId() && flightId != 0) {

                Flight newFlight = flightService.findFlightById(flightId);

                existingTravelPackage.setFlight(newFlight);
            }
            if (hotelId != existingTravelPackage.getHotel().getId() && hotelId != 0) {

                Hotel newHotel = hotelService.findHotelById(hotelId);

                existingTravelPackage.setHotel(newHotel);
            }
            if (transportationId != existingTravelPackage.getTransportation().getId() && transportationId != 0) {

                Transportation newTransportation = transportationService.findTransportationById(transportationId);

                existingTravelPackage.setTransportation(newTransportation);
            }
            if (flightHomeId != existingTravelPackage.getFlightHome().getId() && flightHomeId != 0) {

                Flight flightHome = flightService.findFlightById(flightHomeId);

                existingTravelPackage.setFlightHome(flightHome);
            }
            if (transportationHomeId != existingTravelPackage.getTransportationHome().getId() && transportationHomeId != 0) {

                Transportation transportationHome = transportationService.findTransportationById(transportationHomeId);

                existingTravelPackage.setTransportationHome(transportationHome);
            }

            travelPackageRepository.save(existingTravelPackage);
        }

        return existingTravelPackage;
    }

    public String deleteTravelPackage(long id) {
        TravelPackage travelPackageToDelete = findTravelPackageById(id);

        if (travelPackageToDelete != null) {

            List<User> bookedByUsers = new ArrayList<>();

            for (Booking booking : bookingService.getAllBookings()) {
                if (booking.getTravelPackage().getId() == travelPackageToDelete.getId()) {

                    bookedByUsers.add(booking.getUser());
                }
            }

            if (bookedByUsers.isEmpty()) {

                Flight flight = travelPackageToDelete.getFlight();
                flight.setPackaged(false);
                flightService.save(flight);

                Hotel hotel = travelPackageToDelete.getHotel();
                hotel.setPackaged(false);
                hotelService.save(hotel);

                Transportation transportation = travelPackageToDelete.getTransportation();
                transportation.setPackaged(false);
                transportationService.save(transportation);

                Flight flightHome = travelPackageToDelete.getFlightHome();
                flightHome.setPackaged(false);
                flightService.save(flightHome);

                Transportation transportationHome = travelPackageToDelete.getTransportationHome();
                transportationHome.setPackaged(false);
                transportationService.save(transportationHome);

                travelPackageRepository.save(travelPackageToDelete);

                travelPackageRepository.delete(travelPackageToDelete);

                return "Deleted travelpackage";
            } else {

                return "There are active bookings by Users";
            }


        } else {

            return "Provided travelpackage ID not found";
        }
    }
}
