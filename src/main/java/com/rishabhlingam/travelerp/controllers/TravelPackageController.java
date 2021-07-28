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
	public String getTravelPackageById(@PathVariable String id, Model model) {
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
		return travelPackageService.purchasePackage(id, model);
	}

	@RequestMapping("/travelPackages/new")
	public String getNewPackageForm() {
		return "addTravelPackage";
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/travelPackages/add")
	public void addTravelPackage( @RequestBody Map<String, Object> map){
		travelPackageService.addTravelPackage(map);
	}
}
