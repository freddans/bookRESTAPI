package com.example.bookbook.controller;

import com.example.bookbook.entities.Hotel;
import com.example.bookbook.entities.Transportation;
import com.example.bookbook.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<Transportation> getAllTransportations() {
        return transportationService.getAllTransportations();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Transportation create(@RequestBody Transportation transportation) {
        return transportationService.create(transportation);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transportation findTransportationById(@PathVariable long id) {
        return transportationService.findTransportationById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Transportation updateTransportation(@PathVariable long id, @RequestBody Transportation transportation) {
        return transportationService.updateTransportation(id, transportation);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTransportation(@PathVariable long id) {
        return transportationService.deleteTransportation(id);
    }
}
