package com.rishabhlingam.travelerp.models;

public class PremiumPassenger extends StandardPassenger{
	public static final double discount = 100.00;
	public PremiumPassenger(String name, String email, String conatctNumber, double balance) {
		super(name, email, conatctNumber, balance);
	}
	public double getDiscount() {
		return discount;
	}
}
