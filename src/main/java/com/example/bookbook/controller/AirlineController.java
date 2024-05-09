package com.example.bookbook.controller;

import com.example.bookbook.entities.Airline;
import com.example.bookbook.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    AirlineService airLineService;

    @Autowired
    public AirlineController(AirlineService airLineService) {
        this.airLineService = airLineService;
    }

    @GetMapping("/all")
    public List<Airline> allAirLines() {
        return airLineService.getAllAirLines();
    }

    @PostMapping("/add")
    public Airline create(@RequestBody Airline airLine) {
        return airLineService.create(airLine);
    }
}
