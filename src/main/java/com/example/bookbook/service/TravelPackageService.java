package com.example.bookbook.service;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.TravelPackage;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.Transportation;
import com.example.bookbook.repositories.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPackageService {

    private TravelPackageRepository travelPackageRepository;
    private FlightService flightService;
    private HotelService hotelService;
    private TransportationService transportationService;

    @Autowired
    public TravelPackageService(TravelPackageRepository travelPackageRepository, FlightService flightService, HotelService hotelService, TransportationService transportationService) {
        this.travelPackageRepository = travelPackageRepository;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.transportationService = transportationService;
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

    public TravelPackage findTravelPackageById(long id) {
        Optional<TravelPackage> optionalBooking = travelPackageRepository.findById(id);

        if (optionalBooking.isPresent()) {
            TravelPackage travelPackage = optionalBooking.get();

            return travelPackage;
        } else {

            return null;
        }
    }

    public TravelPackage updateTravelPackage(long id, long flightId, long hotelId, long transportationId) {
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

            travelPackageRepository.save(existingTravelPackage);
        }

        return existingTravelPackage;
    }

    public String deleteTravelPackage(long id) {
        TravelPackage travelPackageToDelete = findTravelPackageById(id);

        if (travelPackageToDelete != null) {

            Flight flight = travelPackageToDelete.getFlight();
            flight.setPackaged(false);

            Hotel hotel = travelPackageToDelete.getHotel();
            hotel.setPackaged(false);

            Transportation transportation = travelPackageToDelete.getTransportation();
            transportation.setPackaged(false);

            travelPackageRepository.delete(travelPackageToDelete);

            return "Deleted travelpackage";
        } else {

            return "Provided travelpackage ID not found";
        }
    }
}
