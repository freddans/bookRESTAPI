package com.example.bookbook.controller;

import com.example.bookbook.entities.TravelPackage;
import com.example.bookbook.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
public class TravelPackageController {

    private TravelPackageService travelPackageService;

    @Autowired
    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageService.getAllTravelPackages();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public TravelPackage createWithHome(@RequestParam("flightId") long flightId, @RequestParam("hotelId") long hotelId, @RequestParam("transportationId") long transportationId,
                                        @RequestParam("flightHomeId") long flightHomeId, @RequestParam("transportationHomeId") long transportationHomeId) {
        return travelPackageService.createWithFlightHome(flightId, hotelId, transportationId, flightHomeId, transportationHomeId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TravelPackage findTravelPackageById(@PathVariable long id) {
        return travelPackageService.findTravelPackageById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TravelPackage updateTravelPackage(@PathVariable long id,
                                             @RequestParam("flightId") long flightId, @RequestParam("hotelId") long hotelId, @RequestParam("transportationId") long transportationId,
                                             @RequestParam("flightHomeId") long flightHomeId, @RequestParam("transportationHomeId") long transportationHomeId) {
        return travelPackageService.updateTravelPackage(id, flightId, hotelId, transportationId, flightHomeId, transportationHomeId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTravelPackage(@PathVariable long id) {
        return travelPackageService.deleteTravelPackage(id);
    }
}
