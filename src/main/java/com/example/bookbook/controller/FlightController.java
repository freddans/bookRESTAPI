package com.example.bookbook.controller;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public List<Flight> allFlights() {
        return flightService.getAllAirLines();
    }

    @PostMapping("/add")
    public Flight create(@RequestBody Flight flight) {
        return flightService.create(flight);
    }
}
