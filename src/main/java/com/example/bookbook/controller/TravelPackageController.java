package com.example.bookbook.controller;

import com.example.bookbook.entities.TravelPackage;
import com.example.bookbook.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageService.getAllTravelPackages();
    }

    @PostMapping("/add")
    public TravelPackage createWithHome(@RequestParam("flightId") long flightId, @RequestParam("hotelId") long hotelId, @RequestParam("transportationId") long transportationId,
                                        @RequestParam("flightHomeId") long flightHomeId, @RequestParam("transportationHomeId") long transportationHomeId) {
        return travelPackageService.createWithFlightHome(flightId, hotelId, transportationId, flightHomeId, transportationHomeId);
    }

    @GetMapping("/{id}")
    public TravelPackage findTravelPackageById(@PathVariable long id) {
        return travelPackageService.findTravelPackageById(id);
    }

    @PutMapping("/update/{id}")
    public TravelPackage updateTravelPackage(@PathVariable long id,
                                             @RequestParam("flightId") long flightId, @RequestParam("hotelId") long hotelId, @RequestParam("transportationId") long transportationId,
                                             @RequestParam("flightHomeId") long flightHomeId, @RequestParam("transportationHomeId") long transportationHomeId) {
        return travelPackageService.updateTravelPackage(id, flightId, hotelId, transportationId, flightHomeId, transportationHomeId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTravelPackage(@PathVariable long id) {
        return travelPackageService.deleteTravelPackage(id);
    }
}
