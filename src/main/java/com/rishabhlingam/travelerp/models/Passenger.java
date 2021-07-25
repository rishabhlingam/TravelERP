package com.rishabhlingam.travelerp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
	@ManyToMany(mappedBy = "passengers")
	private List<TravelPackage> travelPackages;
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
	public List<TravelPackage> getTravelPackages() {
		return travelPackages;
	}
	public void setTravelPackages(List<TravelPackage> travelPackages) {
		this.travelPackages = travelPackages;
	}
}
