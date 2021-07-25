package com.rishabhlingam.travelerp.repository;

import com.rishabhlingam.travelerp.models.Passenger;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengerRepository extends CrudRepository<Passenger, String> {
    Optional<Passenger> findByEmail(String email);
}
