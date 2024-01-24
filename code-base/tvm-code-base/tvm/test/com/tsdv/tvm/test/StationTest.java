package com.tsdv.tvm.test;

import com.tsdv.tvm.db.orm.Station;

import junit.framework.TestCase;


public class StationTest extends TestCase {
	private Station station1;
	private Station station2;
	double fare;
	
	public void setUp() throws Exception {
		station2 = new Station("B", 7.3);
    }
	
	public void testFareRoundUp() {
		station1 = new Station("A", 0);
		fare = station1.fare(station2);
		
		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		assertEquals(fare, 2.7);
		

		station1 = new Station("A", 16.4);
		fare = station1.fare(station2);

		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		assertEquals(fare, 3.1);
	}
	
	public void testFareEvenStep() {
		station1 = new Station("A", 15.3);
		fare = station1.fare(station2);
		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		assertEquals(fare, 2.7);
		
		station1 = new Station("A", 2.3);
		fare = station1.fare(station2);
		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		assertEquals(fare, Station.BASE_FARE);
	}
	
	public void testBaseFare() {
		station1 = new Station("A", 5.1);
		fare = station1.fare(station2);

		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		assertEquals(fare, Station.BASE_FARE);
		
		station1 = new Station("A", 7.0);

		//System.out.println(station1.distanceInKm(station2) + " -> fare: " + fare);
		fare = station1.fare(station2);
		assertEquals(fare, Station.BASE_FARE);
		
	}

}
