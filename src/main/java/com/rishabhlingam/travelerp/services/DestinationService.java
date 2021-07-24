package com.rishabhlingam.travelerp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhlingam.travelerp.models.Destination;
import com.rishabhlingam.travelerp.models.Itinerary;
import com.rishabhlingam.travelerp.repository.DesinationRepository;

@Service
public class DestinationService {
	@Autowired
	private DesinationRepository repository;
	
	public List<Destination> getAllDestinations(){
		List<Destination> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public Destination addDestination(Destination d, Itinerary itinerary) {
		d.setItinerary(itinerary);
		return repository.save(d);
	}
	
	public void addDestination(List<Destination> d, Itinerary itinerary) {
		for(Destination dest : d) {
			addDestination(dest, itinerary);
		}
	}
	
	public Destination findById(String id) {
		Destination destination = repository.findById(id).get();
		return destination;
	}
}
