package com.blz.cabinvoicegenerator;

public class InvoiceGenerator {
	private final static double MINIMUM_COST_PER_KM = 10.0;
	private final static int COST_PER_TIME = 1;
	private static final double MINIMUM_TOTAL_FARE = 5;
	private final static double MINIMUM_COST_PER_KM_FOR_PREMIUM_RIDE = 15.0;
	private final static int COST_PER_TIME_FOR_PREMIUM_RIDE = 2;
	private static final double MINIMUM_TOTAL_FARE_FOR_PREMIUM_RIDE = 20;

	private RideRepository rideRepository;

	enum RideType {
		PREMIUM, NORMAL
	}

	RideType rideType;

	public InvoiceGenerator() {
		this.rideRepository = new RideRepository();
	}

	public double calculateFare(double distance, int time) {
		double totalFare = MINIMUM_COST_PER_KM * distance + COST_PER_TIME * time;
		return Math.max(totalFare, MINIMUM_TOTAL_FARE);
	}

	public double calculateFareForPremium(double distance, int time) {
		double totalFare = MINIMUM_COST_PER_KM_FOR_PREMIUM_RIDE * distance + COST_PER_TIME_FOR_PREMIUM_RIDE * time;
		return Math.max(totalFare, MINIMUM_TOTAL_FARE_FOR_PREMIUM_RIDE);
	}

	public InvoiceSummary calculateFare(Ride[] rides) {
		double totalFare = 0;
		for (Ride ride : rides) {
			totalFare = totalFare + calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType());
		}
		return new InvoiceSummary(rides.length, totalFare);
	}

	private double calculateFare(double distance, int time, RideType rideType) {
		if (rideType == RideType.NORMAL)
			return calculateFare(distance, time);
		return calculateFareForPremium(distance, time);
	}

	public void addRides(String userId, Ride[] rides) {
		rideRepository.addRides(userId, rides);
	}

	public InvoiceSummary getInvoiceSummary(String userId) {
		return calculateFare(rideRepository.getRides(userId));
	}

	public void printWelcome() {
		System.out.println("Welcome to the Cab Invoice Generator Program");
	}
}
