package com.rishabhlingam.travelerp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Passenger {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	@Column(unique = true)
	private String email;
	private double balance;
	private String userType;
	@JsonIgnore
	@ManyToMany(mappedBy = "passengers")
	private Set<TravelPackage> travelPackages = new HashSet<>();
	@JsonIgnore
	@ManyToMany(mappedBy = "passengers")
	private Set<Activity> activities = new HashSet<>();
	public Passenger() {}
	public Passenger(String name, String email, double balance, String userType) {
		super();
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.userType = userType;
	}
	public Passenger(String name, String email) {
		this(name, email, 5000.00, "standard");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<TravelPackage> getTravelPackages() {
		return travelPackages;
	}
	public void setTravelPackages(Set<TravelPackage> travelPackages) {
		this.travelPackages = travelPackages;
	}
	public Set<Activity> getActivities() {
		return activities;
	}
	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		double factor = 1.0;
		if(getUserType().equals("gold")){
			factor = 0.9;
		} else if(getUserType().equals("premium")){
			factor = 0.0;
		}
		builder.append("Name: " + getName() + "\n")
				.append("Id: " + getId() + "\n")
				.append("Balance: " + getBalance() + "\n");
		builder.append("\nList of activities enrolled in:\n");
		for(Activity activity : getActivities()){
			builder.append(activity.getName() + "(destination: " + activity.getDestination().getName() + ")\n");
			builder.append("Price paid: " + (activity.getCost() * factor) + "\n");
		}
		return builder.toString();
	}
}
