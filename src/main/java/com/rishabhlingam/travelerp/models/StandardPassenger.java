package com.rishabhlingam.travelerp.models;

public class StandardPassenger extends Passenger {
	public static final double discount = 00.00;
	public StandardPassenger(String name, String email, String conatctNumber, double balance) {
		super(name, email, conatctNumber, balance);
	}
	public double getDiscount() {
		return discount;
	}
	
}
