package com.rishabhlingam.travelerp.controllers;

import java.util.ArrayList;
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
import com.rishabhlingam.travelerp.services.ActivityService;
import com.rishabhlingam.travelerp.services.DestinationService;

@RestController
public class DestinationController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private DestinationService destinationService;
	
	@RequestMapping("/destinations")
	public List<Destination> getAllDestinations() {
		return destinationService.getAllDestinations();
	}
	
	@RequestMapping("/destinations/{id}")
	public Destination getDestinationBYId(@PathVariable String id) {
		return destinationService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/destinations")
	public void addDestination( @RequestBody Map<String, Object> map){
		Destination destination = extractDestinationObject(map);
		List<Activity> activities = extractActivityList(map);
		//destination = destinationService.addDestination(destination);
		activityService.addActivities(activities, destination);
	}
	
	public Destination extractDestinationObject(Map<String, Object> map) {
		String destinationName = (String) map.get("name");
		return new Destination(destinationName);
	}
	
	public List<Activity> extractActivityList(Map<String, Object> map) {
		List<Map<String, Object>> list = (List) map.get("activities");
		List<Activity> activities = new ArrayList<>();
		for(Map<String, Object> item : list) {
			String name = (String) item.get("name");
			String description = (String) item.get("description");
			Double cost = (Double) item.get("cost");
			Integer capacity = (Integer) item.get("capacity");
			
			activities.add( new Activity(name, description, cost, capacity) );
		}
		return activities;
	}
	
}
