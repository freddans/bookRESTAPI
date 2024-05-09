package com.example.bookbook.service;

import com.example.bookbook.entities.Hotel;
import com.example.bookbook.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel create(Hotel hotel) {
        Hotel newHotel = new Hotel(hotel.getName(), hotel.getCountry(), hotel.getCity(), hotel.isWifi(), hotel.getStars(), hotel.getPrice());
        hotelRepository.save(newHotel);

        return newHotel;
    }
}
