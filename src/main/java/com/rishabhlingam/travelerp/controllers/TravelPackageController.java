package com.rishabhlingam.travelerp.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rishabhlingam.travelerp.models.*;
import com.rishabhlingam.travelerp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
public class TravelPackageController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private DestinationService destinationService;
	@Autowired
	private ItineraryService itineraryService;
	@Autowired
	private TravelPackageService travelPackageService;
	@Autowired
	private PassengerService passengerService;
	
	@RequestMapping("/travelPackages")
	public String getAllDestinations(Model model) {
		List<TravelPackage> packages = travelPackageService.getAllTravelPackages();
		model.addAttribute("packages", packages);
		return "viewTravelPackages";
	}
	
	@RequestMapping("/travelPackages/{id}")
	public String getItineraryById(@PathVariable String id, Model model) {
		Optional<TravelPackage> optional = travelPackageService.findById(id);
		if(!optional.isPresent()){
			model.addAttribute("errorMessage", "Something went wrong. Could not find the package");
			return "error";
		}
		TravelPackage travelPackage = optional.get();
		model.addAttribute("vacancy", travelPackage.getCapacity() - travelPackage.getPassengers().size());
		model.addAttribute("package", travelPackage);
		return "travelPackage";
	}

	@RequestMapping("/travelPackages/{id}/purchase")
	public String purchasePackage(@PathVariable String id, Model model) {
		Optional<TravelPackage> optional = travelPackageService.findById(id);
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
			travelPackageService.addTravelPackage(travelPackage);
			return "redirect:/travelPackages/"+id;
		} else {
			model.addAttribute("errorMessage", "Can not purchase, max capacity reached. Try another package :(");
			return "error";
		}
	}

	@ResponseBody
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
