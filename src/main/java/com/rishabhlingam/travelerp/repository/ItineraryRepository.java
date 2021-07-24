package com.rishabhlingam.travelerp.repository;

import org.springframework.data.repository.CrudRepository;

import com.rishabhlingam.travelerp.models.Itinerary;

public interface ItineraryRepository extends CrudRepository<Itinerary, String> {

}
