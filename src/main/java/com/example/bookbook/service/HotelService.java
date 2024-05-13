package com.example.bookbook.service;

import com.example.bookbook.entities.Flight;
import com.example.bookbook.entities.Hotel;
import com.example.bookbook.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if (hotel.isPackaged() == null) {

            Hotel newHotel = new Hotel(hotel.getName(), hotel.getCountry(), hotel.getCity(), hotel.isWifi(), hotel.getStars(), hotel.getPrice());
            hotelRepository.save(newHotel);

            return newHotel;
        } else {

            Hotel newHotel = new Hotel(hotel.getName(), hotel.getCountry(), hotel.getCity(), hotel.isWifi(), hotel.getStars(), hotel.getPrice(), hotel.isPackaged());
            hotelRepository.save(newHotel);

            return newHotel;
        }

    }

    public Hotel findHotelById(long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();

            return hotel;
        } else {

            return null;
        }
    }

    public Hotel updateHotel(long id, Hotel newHotelInformation) {
        Hotel existingHotel = findHotelById(id);

        if (existingHotel != null) {

            if (!newHotelInformation.getName().contains(existingHotel.getName()) && newHotelInformation.getName() != null) {

                existingHotel.setName(newHotelInformation.getName());
            }
            if (!newHotelInformation.getCountry().contains(existingHotel.getCountry()) && newHotelInformation.getCountry() != null) {

                existingHotel.setCountry(newHotelInformation.getCountry());
            }
            if (!newHotelInformation.getCity().contains(existingHotel.getCity()) && newHotelInformation.getCity() != null) {

                existingHotel.setCity(newHotelInformation.getCity());
            }
            if (newHotelInformation.isWifi()) {

                existingHotel.setWifi(true);
            } else {

                existingHotel.setWifi(false);
            }
            if (newHotelInformation.getStars() != existingHotel.getStars() && newHotelInformation.getStars() != 0) {

                existingHotel.setStars(newHotelInformation.getStars());
            }
            if (newHotelInformation.getPrice() != existingHotel.getPrice() && newHotelInformation.getPrice() != 0) {

                existingHotel.setPrice(newHotelInformation.getPrice());
            }

            hotelRepository.save(existingHotel);
        }

        return existingHotel;
    }

    public String deleteHotel(long id) {
        Hotel hotelToDelete = findHotelById(id);

        if (hotelToDelete != null) {

            if (!hotelToDelete.isPackaged()) {


                hotelRepository.delete(hotelToDelete);
            } else {

                return "Hotel is currently part of a travelpackage and can't be deleted";
            }
        } else {

            return "Could not find hotel with provided ID";
        }

        return "Hotel deleted";
    }

    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }
}
