package com.blz.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
	Map<String, ArrayList<Ride>> userRides = null;

	public RideRepository() {
		userRides = new HashMap<>();
	}

	public void addRides(String userId, Ride[] rides) {
		ArrayList<Ride> rideList = userRides.get(userId);
		if (rideList == null)
			rideList = new ArrayList<>(Arrays.asList(rides));
		else
			 rideList.addAll(Arrays.asList(rides));
		userRides.put(userId, rideList);
	}

	public Ride[] getRides(String userId) {
		return userRides.get(userId).toArray(new Ride[0]);
	}
}