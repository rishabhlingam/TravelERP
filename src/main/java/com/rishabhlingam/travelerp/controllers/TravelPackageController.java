package com.rishabhlingam.travelerp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rishabhlingam.travelerp.models.Activity;
import com.rishabhlingam.travelerp.models.Destination;
import com.rishabhlingam.travelerp.models.Itinerary;
import com.rishabhlingam.travelerp.models.TravelPackage;
import com.rishabhlingam.travelerp.services.ActivityService;
import com.rishabhlingam.travelerp.services.DestinationService;
import com.rishabhlingam.travelerp.services.ItineraryService;
import com.rishabhlingam.travelerp.services.TravelPackageService;

@RestController
public class TravelPackageController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private DestinationService destinationService;
	@Autowired
	private ItineraryService itineraryService;
	@Autowired
	private TravelPackageService travelPackageService;
	
	@RequestMapping("/travelPackages")
	public List<TravelPackage> getAllDestinations() {
		return travelPackageService.getAllTravelPackages();
	}
	
	@RequestMapping("/travelPackages/{id}")
	public Destination getItineraryById(@PathVariable String id) {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/travelPackages")
	public void addItinerary( @RequestBody Map<String, Object> map){
		String name = (String) map.get("name");
		Integer passengerCapacity = (Integer) map.get("passengerCapacity");
		TravelPackage travelPackage = new TravelPackage(name, passengerCapacity);
		travelPackage = travelPackageService.addTravelPackage(travelPackage);
		Itinerary itinerary = itineraryService.addItinerary( new Itinerary(), travelPackage );
		addDestinationList((List)map.get("itinerary"), itinerary);
	}
	
	public void addDestinationList(List<Map<String, Object>> list, Itinerary itinerary) {
		for(Map<String, Object> object : list) {
			String destName = (String) object.get("name");
			Destination destination = new Destination(destName);
			destination = destinationService.addDestination(destination, itinerary);
			addActivityList((List) object.get("activities"), destination);
		}
	}
	
	public void addActivityList(List<Map<String, Object>> list, Destination destination) {
		for(Map<String, Object> item : list) {
			String name = (String) item.get("name");
			String description = (String) item.get("description");
			Double cost = (Double) item.get("cost");
			Integer capacity = (Integer) item.get("capacity");
			Activity activity =  new Activity(name, description, cost, capacity);
			activityService.addActivity(activity, destination);
		}
	}
}
