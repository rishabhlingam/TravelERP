package com.rishabhlingam.travelerp.controllers;

import java.util.List;
import java.util.Optional;

import com.rishabhlingam.travelerp.models.Passenger;
import com.rishabhlingam.travelerp.models.TravelPackage;
import com.rishabhlingam.travelerp.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rishabhlingam.travelerp.models.Activity;
import com.rishabhlingam.travelerp.services.ActivityService;

@Controller
public class ActivityController {
	@Autowired
	PassengerService passengerService;
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/activities")
	public List<Activity> getAllActivities() {
		return activityService.getAllActivity();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/activities")
	public void addActivity(@RequestBody Activity activity){
		//activityService.addActivity(activity);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/activities/{id}/delete")
	public void deleteActivity(@PathVariable String id){
		activityService.deleteActivity(id);
	}

	@RequestMapping("/activities/{id}")
	public String getActivity(@PathVariable String id, Model model){
		Optional<Activity> optionalActivity = activityService.findById(id);
		if(!optionalActivity.isPresent()){
			model.addAttribute("errorMessage", "Something went wrong, Activity not found");
			return "error";
		}
		Activity activity = optionalActivity.get();
		model.addAttribute("activity", activity);
		model.addAttribute("vacancy", activity.getCapacity() - activity.getPassengers().size());
		return "activity";
	}

	@RequestMapping("/activities/{id}/purchase")
	public String purchaseActivity(@PathVariable String id, Model model) {
		Optional<Activity> optionalActivity = activityService.findById(id);
		if(!optionalActivity.isPresent()){
			model.addAttribute("errorMessage", "Something went wrong, Activity not found");
			return "error";
		}
		Activity activity = optionalActivity.get();
		SecurityContext context = SecurityContextHolder.getContext();
		String email = context.getAuthentication().getName();
		Passenger passenger = passengerService.findByEmail(email);
		if(! activity.getDestination().getItinerary().getTravelPackage().getPassengers().contains(passenger)){
			model.addAttribute("errorMessage", "Purchase the Travel Package First.");
			return "error";
		}
		if(activity.getPassengers().size() >= activity.getCapacity()){
			model.addAttribute("errorMessage", "Can not enroll, max capacity reached. Try another activity :(");
			return "error";
		}
		if(activity.getPassengers().contains(passenger)){
			model.addAttribute("errorMessage", "You have already enrolled for this Activity :)");
			return "error";
		}

		boolean canPay = false;
		switch (passenger.getUserType()){
			case "premium":{
				canPay = true; break;
			}
			case "gold": {
				canPay = passenger.getBalance() < (activity.getCost() * 0.9) ? false : true;
				break;
			}
			case "standard": {
				canPay = passenger.getBalance() < activity.getCost() ? false : true;
				break;
			}
		}
		if(!canPay){
			model.addAttribute("errorMessage", "Can not enroll, insufficient balance :(");
			return "error";
		} else {
			switch (passenger.getUserType()){
				case "premium":{
					break;
				}
				case "gold": {
					passenger.setBalance( passenger.getBalance() - (activity.getCost() * 0.9) );
					break;
				}
				case "standard": {
					passenger.setBalance( passenger.getBalance() - activity.getCost() );
					break;
				}
			}
		}
		passengerService.addPassenger(passenger);
		activity.addPassengers(passenger);
		activityService.updateActivity(activity);
		return "redirect:/activities/"+id;
	}
}
