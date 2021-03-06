package com.rishabhlingam.travelerp.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
public class Activity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private String description;
	private double cost;
	private int capacity;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destination_id", referencedColumnName = "id")
	private Destination destination;
	@ManyToMany
	@JoinTable(
			name="enrolledPassengers",
			joinColumns = @JoinColumn(name = "activity_id"),
			inverseJoinColumns = @JoinColumn(name = "passenger_id")
	)
	private Set<Passenger> passengers;
	public Activity() {}
		
	public Activity(String name, String description, double cost, int capacity) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.capacity = capacity;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public Set<Passenger> getPassengers() {
		return passengers;
	}
	public void addPassengers(Passenger passenger) {
		this.passengers.add(passenger);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Activity name: " + getName());
		builder.append("\n");
		builder.append("Activity description: " + getDescription());
		builder.append("\n");
		builder.append("Activity cost: " + getCost());
		builder.append("\n");
		builder.append("Total capacity: " + getCapacity());
		builder.append("\n");
		return builder.toString();
	}
}
