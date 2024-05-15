package com.example.bookbook.service;

import com.example.bookbook.entities.Transportation;
import com.example.bookbook.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if (transportation.isPackaged() == null) {

            Transportation newTransportation = new Transportation(transportation.getType(), transportation.getPrice());
            transportationRepository.save(newTransportation);

            return newTransportation;
        } else {

            Transportation newTransportation = new Transportation(transportation.getType(), transportation.getPrice(), transportation.isPackaged());
            transportationRepository.save(newTransportation);

            return newTransportation;
        }

    }

    public Transportation findTransportationById(long id) {
        Optional<Transportation> optionalTransportation = transportationRepository.findById(id);

        if (optionalTransportation.isPresent()) {
            Transportation transportation = optionalTransportation.get();

            return transportation;
        } else {

            return null;
        }
    }

    public Transportation updateTransportation(long id, Transportation newTransportationInformation) {
        Transportation existingTransportation = findTransportationById(id);

        if (existingTransportation != null) {

            if (!newTransportationInformation.getType().contains(existingTransportation.getType()) && newTransportationInformation.getType() != null) {

                existingTransportation.setType(newTransportationInformation.getType());
            }
            if (newTransportationInformation.getPrice() != existingTransportation.getPrice() && existingTransportation.getPrice() != 0) {

                existingTransportation.setPrice(newTransportationInformation.getPrice());
            }

            transportationRepository.save(existingTransportation);
        }

        return existingTransportation;
    }

    public void save(Transportation transportation) {
        transportationRepository.save(transportation);
    }

    public String deleteTransportation(long id) {
        Transportation transportationToDelete = findTransportationById(id);

        if (transportationToDelete != null) {

            if (!transportationToDelete.isPackaged()) {


                transportationRepository.delete(transportationToDelete);
            } else {

                return "Transportation is currently part of a travelpackage and can't be deleted";
            }
        } else {

            return "Could not find transportation with provided ID";
        }

        return "Transportation deleted";
    }
}
