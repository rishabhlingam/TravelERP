package com.rishabhlingam.travelerp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhlingam.travelerp.models.Itinerary;
import com.rishabhlingam.travelerp.models.TravelPackage;
import com.rishabhlingam.travelerp.repository.ItineraryRepository;

@Service
public class ItineraryService {
	@Autowired
	private ItineraryRepository repository;
	
	public List<Itinerary> getAllItineraries(){
		List<Itinerary> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Itinerary addItinerary(Itinerary i, TravelPackage travelPackage) {
		i.setTravelPackage(travelPackage);
		return repository.save(i);
	}
	
	public Itinerary findById(String id) {
		Itinerary itinerary = repository.findById(id).get();
		return itinerary;
	}
}
