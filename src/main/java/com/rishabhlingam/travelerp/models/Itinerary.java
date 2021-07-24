package com.rishabhlingam.travelerp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Itinerary {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@OneToMany(mappedBy = "itinerary")
	private List<Destination> destinations;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "travelPackage_id", referencedColumnName = "id")
	TravelPackage travelPackage;

	public TravelPackage getTravelPackage() {
		return travelPackage;
	}
	public void setTravelPackage(TravelPackage travelPackage) {
		this.travelPackage = travelPackage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("List of destinations are: ");
		builder.append("\n");
		for(Destination destination : destinations) {
			builder.append("-->");
			builder.append(destination.toString());
			builder.append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}
}
