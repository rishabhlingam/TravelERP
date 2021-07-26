package com.rishabhlingam.travelerp.services;

import com.rishabhlingam.travelerp.models.Passenger;
import com.rishabhlingam.travelerp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PassengerService {
    @Autowired
    private PassengerRepository repository;


    public List<Passenger> getAllPassengers() {
        List<Passenger> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Passenger addPassenger(Passenger passenger){
        return repository.save(passenger);
    }

    public Passenger getPassenger(String id){
        return repository.findById(id).get();
    }

    public Passenger findByEmail(String email){
        return repository.findByEmail(email).get();
    }
}
