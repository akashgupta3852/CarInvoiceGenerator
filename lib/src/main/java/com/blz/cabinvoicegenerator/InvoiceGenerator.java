package com.blz.cabinvoicegenerator;

public class InvoiceGenerator {
	private final static double MINIMUM_COST_PER_KM = 10.0;
	private final static int COST_PER_TIME = 1;
	private static final double MINIMUM_TOTAL_FARE = 5;

	public double calculateFare(double distance, int time) {
		double totalFare = MINIMUM_COST_PER_KM * distance + COST_PER_TIME * time;
		return Math.max(totalFare, MINIMUM_TOTAL_FARE);
	}

	public double calculateFare(Ride[] rides) {
		double totalFare = 0;
		for (Ride ride : rides) {
			totalFare = totalFare + calculateFare(ride.getDistance(), ride.getTime());
		}
		;
		return totalFare;
	}

	public void printWelcome() {
		System.out.println("Welcome to the Cab Invoice Generator Program");
	}
}
