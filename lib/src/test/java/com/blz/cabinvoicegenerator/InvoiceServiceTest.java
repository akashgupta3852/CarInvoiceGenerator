package com.blz.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
	private InvoiceGenerator invoiceGenerator = null;

	@Before
	public void initialize() {
		invoiceGenerator = new InvoiceGenerator();
	}

	@Test
	public void givenDistanceAndTime_ShouldReturnTheTotalFare() {
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}

	@Test
	public void givenLessDistanceOrTime_ShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 1;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(2.0, 5, InvoiceGenerator.RideType.NORMAL),
				new Ride(0.1, 1, InvoiceGenerator.RideType.NORMAL) };
		InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}

	@Test
	public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
		String userId = "a@b.com";
		Ride[] rides = { new Ride(2.0, 5, InvoiceGenerator.RideType.NORMAL),
				new Ride(0.1, 1, InvoiceGenerator.RideType.NORMAL) };
		invoiceGenerator.addRides(userId, rides);
		InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}

	@Test
	public void givenUserIdAndRides_ShouldReturnMultipleInvoiceSummary() {
		String userId = "a@b.com";
		Ride[] rides1 = { new Ride(2.0, 5, InvoiceGenerator.RideType.NORMAL),
				new Ride(0.1, 1, InvoiceGenerator.RideType.NORMAL) };
		invoiceGenerator.addRides(userId, rides1);
		Ride[] rides2 = { new Ride(2.0, 5, InvoiceGenerator.RideType.PREMIUM),
				new Ride(0.1, 1, InvoiceGenerator.RideType.PREMIUM) };
		invoiceGenerator.addRides(userId, rides2);
		Ride[] rides3 = { new Ride(2.0, 5, InvoiceGenerator.RideType.PREMIUM),
				new Ride(0.1, 1, InvoiceGenerator.RideType.NORMAL) };
		invoiceGenerator.addRides(userId, rides3);
		InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(6, 135);
		Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	}
}
