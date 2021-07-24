package com.rishabhlingam.travelerp.models;

public class GoldPassenger extends StandardPassenger{
	public static final double discount = 10.00;
	public GoldPassenger(String name, String email, String conatctNumber, double balance) {
		super(name, email, conatctNumber, balance);
	}
	public double getDiscount() {
		return discount;
	}
}
