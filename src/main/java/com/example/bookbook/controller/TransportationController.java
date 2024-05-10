package com.example.bookbook.controller;

import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.Transportation;
import com.example.bookbook.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportation")
public class TransportationController {

    private TransportationService transportationService;

    @Autowired
    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @GetMapping("/all")
    public List<Transportation> getAllTransportations() {
        return transportationService.getAllTransportations();
    }

    @PostMapping("/add")
    public Transportation create(@RequestBody Transportation transportation) {
        return transportationService.create(transportation);
    }

    @GetMapping("/{id}")
    public Transportation findTransportationById(@PathVariable long id) {
        return transportationService.findTransportationById(id);
    }

    @PutMapping("/update/{id}")
    public Transportation updateTransportation(@PathVariable long id, @RequestBody Transportation transportation) {
        return transportationService.updateTransportation(id, transportation);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTransportation(@PathVariable long id) {
        return transportationService.deleteTransportation(id);
    }
}
