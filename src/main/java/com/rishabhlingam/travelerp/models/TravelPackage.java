package com.rishabhlingam.travelerp.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TravelPackage {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String name;
	private int capacity;
	@OneToOne(mappedBy = "travelPackage")
	private Itinerary itinerary;
	@ManyToMany
	@JoinTable(
			name="selectedPassengers",
			joinColumns = @JoinColumn(name = "travelPackage_id"),
			inverseJoinColumns = @JoinColumn(name = "passenger_id")
	)
	private Set<Passenger> passengers = new HashSet<>();
	public TravelPackage() {}
	public TravelPackage(String name, int capacity) {
		super();
		this.name = name;
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
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
		builder.append("Travel Package Name: " + getName());
		builder.append("\n");
		builder.append("Total Capacity: " + getCapacity());
		builder.append("\n");
		builder.append( getItinerary().toString());
		builder.append("\n");
		return builder.toString();
	}

	public String printPassengerList(){
		StringBuilder builder = new StringBuilder();
		builder.append("Package Name: " + getName() + "\n")
				.append("Passenger Capacity: " + getCapacity() + "\n")
				.append("No. passengers enrolled: " + getPassengers().size() + "\n")
				.append("\nPassenger List:\n");
		for(Passenger passenger : getPassengers()){
			builder.append(passenger.getName() + " (" + passenger.getId() + ")\n");
		}
		return builder.toString();
	}
}
