package com.rishabhlingam.travelerp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rishabhlingam.travelerp.models.Itinerary;
import com.rishabhlingam.travelerp.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.rishabhlingam.travelerp.models.TravelPackage;
import com.rishabhlingam.travelerp.repository.TravelPackageRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TravelPackageService {
	@Autowired
	TravelPackageRepository repository;
	@Autowired
	ItineraryService itineraryService;
	@Autowired
	DestinationService destinationService;
	@Autowired
	PassengerService passengerService;
	
	public List<TravelPackage> getAllTravelPackages(){
		List<TravelPackage> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public TravelPackage addTravelPackage(TravelPackage i) {
		return repository.save(i);
	}
	
	public Optional<TravelPackage> findById(String id) {
		Optional<TravelPackage> travelPackage = repository.findById(id);
		return travelPackage;
	}

	public void addTravelPackage( Map<String, Object> map){
		String name = (String) map.get("name");
		Integer passengerCapacity = (Integer) map.get("passengerCapacity");
		TravelPackage travelPackage = new TravelPackage(name, passengerCapacity);
		travelPackage = addTravelPackage(travelPackage);
		Itinerary itinerary = itineraryService.addItinerary( new Itinerary(), travelPackage );
		destinationService.addDestinationList((List)map.get("itinerary"), itinerary);
	}

	public String purchasePackage(@PathVariable String id, Model model) {
		Optional<TravelPackage> optional = findById(id);
		if(!optional.isPresent()){
			model.addAttribute("errorMessage", "Something went wrong. Could not find the package");
			return "error";
		}
		TravelPackage travelPackage = optional.get();
		SecurityContext context = SecurityContextHolder.getContext();
		String email = context.getAuthentication().getName();
		Passenger passenger = passengerService.findByEmail(email);
		if(travelPackage.getPassengers().size() < travelPackage.getCapacity()){
			travelPackage.addPassengers(passenger);
			addTravelPackage(travelPackage);
			return "redirect:/travelPackages/"+id;
		} else {
			model.addAttribute("errorMessage", "Can not purchase, max capacity reached. Try another package :(");
			return "error";
		}
	}
}
