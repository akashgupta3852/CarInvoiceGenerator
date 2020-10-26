package com.blz.cabinvoicegenerator;

import com.blz.cabinvoicegenerator.InvoiceGenerator.RideType;

public class Ride {
	private double distance;
	private int time;
	private RideType rideType;

	public Ride(double distance, int time) {
		this.distance = distance;
		this.time = time;
	}

	public Ride(double distance, int time, RideType rideType) {
		this(distance, time);
		this.rideType = rideType;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public RideType getRideType() {
		return rideType;
	}

	public void setRideType(RideType rideType) {
		this.rideType = rideType;
	}
}
