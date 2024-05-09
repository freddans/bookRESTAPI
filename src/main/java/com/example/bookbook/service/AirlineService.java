package com.example.bookbook.service;

import com.example.bookbook.entities.Airline;
import com.example.bookbook.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    AirlineRepository airLineRepository;

    @Autowired
    public AirlineService(AirlineRepository airLineRepository) {
        this.airLineRepository = airLineRepository;
    }

    public List<Airline> getAllAirLines() {
        return airLineRepository.findAll();
    }

    public Airline create(Airline airLine) {
        Airline newAirline = new Airline(airLine.getName(), airLine.getPrice());
        airLineRepository.save(newAirline);

        return newAirline;
    }
}
