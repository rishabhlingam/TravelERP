package com.rishabhlingam.travelerp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Destination {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itinerary_id", referencedColumnName = "id")
	private Itinerary itinerary;
	@OneToMany(mappedBy = "destination")
	private List<Activity> activities = new ArrayList<>();;
	public Destination() {}
	public Destination(String name) {
		super();
		this.name = name;
	}
	public Destination(String name, List<Activity> activities) {
		super();
		this.name = name;
		this.activities = activities;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Destination Name: " + getName());
		builder.append("\n");
		builder.append("List of activities available:");
		for(Activity activity : activities) {
			builder.append("-->");
			builder.append(activity.toString());
			builder.append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}
}
