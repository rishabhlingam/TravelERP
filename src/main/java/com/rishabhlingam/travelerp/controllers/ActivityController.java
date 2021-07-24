package com.rishabhlingam.travelerp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rishabhlingam.travelerp.models.Activity;
import com.rishabhlingam.travelerp.services.ActivityService;

@RestController
public class ActivityController {
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
}
