package com.rishabhlingam.travelerp.models;

public abstract class Passenger {
	private String name;
	private String email;
	private String conatctNumber;
	private double balance;
	public Passenger(String name, String email, String conatctNumber, double balance) {
		super();
		this.name = name;
		this.email = email;
		this.conatctNumber = conatctNumber;
		this.balance = balance;
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
	public String getConatctNumber() {
		return conatctNumber;
	}
	public void setConatctNumber(String conatctNumber) {
		this.conatctNumber = conatctNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public abstract double getDiscount();
}
