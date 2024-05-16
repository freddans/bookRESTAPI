package com.example.bookbook.controller;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<Flight> allFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Flight create(@RequestBody Flight flight) {
        return flightService.create(flight);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Flight findFlightById(@PathVariable long id) {
        return flightService.findFlightById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Flight updateFlight(@PathVariable long id, @RequestBody Flight flight) {
        return flightService.updateFlight(id, flight);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFlight(@PathVariable long id) {
        return flightService.deleteFlight(id);
    }
}
