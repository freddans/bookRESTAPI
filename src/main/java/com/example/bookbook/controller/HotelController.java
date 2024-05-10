package com.example.bookbook.controller;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping("/add")
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    @GetMapping("/{id}")
    public Hotel findHotelById(@PathVariable long id) {
        return hotelService.findHotelById(id);
    }

    @PutMapping("/update/{id}")
    public Hotel updateHotel(@PathVariable long id, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel);
    }

    @DeleteMapping("/delete/{id}")
    public String deletehotel(@PathVariable long id) {
        return hotelService.deleteHotel(id);
    }
}
