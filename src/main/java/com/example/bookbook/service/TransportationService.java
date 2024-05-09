package com.example.bookbook.service;

import com.example.bookbook.entities.Transportation;
import com.example.bookbook.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportationService {

    private TransportationRepository transportationRepository;

    @Autowired
    public TransportationService(TransportationRepository transportationRepository) {
        this.transportationRepository = transportationRepository;
    }

    public List<Transportation> getAllTransportations() {
        return transportationRepository.findAll();
    }

    public Transportation create(Transportation transportation) {
        Transportation newTransportation = new Transportation(transportation.getType(), transportation.getPrice());
        transportationRepository.save(newTransportation);
        return newTransportation;
    }
}
