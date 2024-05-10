package com.example.bookbook.service;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllAirLines() {
        return flightRepository.findAll();
    }

    public Flight create(Flight flight) {
        Flight newFlight = new Flight(flight.getName(), flight.getDestination(), flight.getDeparture(), flight.getArrival(), flight.getAirline(), flight.getPrice());
        flightRepository.save(newFlight);

        return newFlight;

    }
}
