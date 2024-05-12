package com.example.bookbook.service;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    FlightRepository flightRepository;
    BookingService bookingService;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight create(Flight flight) {
        Flight newFlight = new Flight(flight.getName(), flight.getDestination(), flight.getDeparture(), flight.getArrival(), flight.getAirline(), flight.getPrice());
        flightRepository.save(newFlight);

        return newFlight;

    }

    public Flight findFlightById(long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {

            Flight flight = optionalFlight.get();

            return flight;
        } else {

            return null;
        }
    }

    public Flight updateFlight(long id, Flight newFlightInformation) {
        Flight existingFlight = findFlightById(id);

        if (existingFlight != null) {

            if (!newFlightInformation.getName().contains(existingFlight.getName()) && newFlightInformation.getName() != null) {

                existingFlight.setName(newFlightInformation.getName());
            }
            if (!newFlightInformation.getDestination().contains(existingFlight.getDestination()) && newFlightInformation.getDestination() != null) {

                existingFlight.setDestination(newFlightInformation.getDestination());
            }
            if (!newFlightInformation.getDeparture().contains(existingFlight.getDeparture()) && newFlightInformation.getDeparture() != null) {

                existingFlight.setDeparture(newFlightInformation.getDeparture());
            }
            if (!newFlightInformation.getArrival().contains(existingFlight.getArrival()) && newFlightInformation.getArrival() != null) {

                existingFlight.setArrival(newFlightInformation.getArrival());
            }
            if (!newFlightInformation.getAirline().contains(existingFlight.getAirline()) && newFlightInformation.getAirline() != null) {

                existingFlight.setAirline(newFlightInformation.getAirline());
            }
            if (newFlightInformation.getPrice() != existingFlight.getPrice() && newFlightInformation.getPrice() != 0) {

                existingFlight.setPrice(newFlightInformation.getPrice());
            }

            flightRepository.save(existingFlight);
        }

        return existingFlight;
    }

    public String deleteFlight(long id) {
        Flight flightToDelete = findFlightById(id);

        if (flightToDelete != null) {

            if (!flightToDelete.isBooked()) {


                flightRepository.delete(flightToDelete);
            } else {

                return "Flight is currently part of a booking and can't be deleted";
            }
        } else {

            return "Could not find flight with provided ID";
        }

        return "Flight deleted";
    }
}
