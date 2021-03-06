package com.rishabhlingam.travelerp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhlingam.travelerp.models.Activity;
import com.rishabhlingam.travelerp.models.Destination;
import com.rishabhlingam.travelerp.repository.ActivityRepository;

@Service
public class ActivityService {
	@Autowired
	private ActivityRepository repository;
	
	public List<Activity> getAllActivity() {
		List<Activity> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public void addActivity(Activity activity, Destination destination) {
		activity.setDestination(destination);
		repository.save(activity);
	}
	
	public void addActivities(List<Activity> activities, Destination destination) {
		for(Activity activity : activities) {
			addActivity(activity, destination);
		}
	}
	
	public void deleteActivity(String id) {
		repository.deleteById(id);
	}

	public Optional<Activity> findById(String id){
		return repository.findById(id);
	}

	public void updateActivity(Activity activity){
		repository.save(activity);
	}

	public void addActivityList(List<Map<String, Object>> list, Destination destination) {
		for(Map<String, Object> item : list) {
			String name = (String) item.get("name");
			String description = (String) item.get("description");
			Double cost = (Double) item.get("cost");
			Integer capacity = (Integer) item.get("capacity");
			Activity activity =  new Activity(name, description, cost, capacity);
			addActivity(activity, destination);
		}
	}
}
