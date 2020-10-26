package com.blz.cabinvoicegenerator;

public class InvoiceSummary {
	private double totalFare;
	private int rides;
	private double averageFare;

	public InvoiceSummary(int numOfRides, double totalFare) {
		this.totalFare = totalFare;
		this.rides = numOfRides;
		this.averageFare = totalFare / numOfRides;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		InvoiceSummary that = (InvoiceSummary) o;
		return rides == that.rides && Double.compare(that.totalFare, totalFare) == 0
				&& Double.compare(that.averageFare, averageFare) == 0;
	}
}
