package com.airline;

import com.airline.data.Flight;
import com.airline.data.FlightsAvailable;
import com.airline.service.ShortestFlight;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ItineraryFinderTests {

	@Test
	public void oneFlightEarliestFLight() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Flight flight = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(c.getTime().equals(queue.peek().getArrivalTime()));
	}

	@Test
	public void oneFlightEarliestFLightIncorectDestination() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Flight flight = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "IAD");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.isEmpty());
	}

	@Test
	public void oneFlightEarliestFLightIncorectOrigin() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Flight flight = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"IAD", "SFO");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.isEmpty());
	}

	@Test
	public void oneFlightEarliestFLightSameOrginAndDestination() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Flight flight = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "SFO");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.isEmpty());
	}

	@Test
	public void twoFlightEarliestFlightSameTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Flight flight1 = new Flight(1, "SFO", "OAK", c.getTime(), c.getTime());
		Flight flight2 = new Flight(1, "SFO", "OAK", c.getTime(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
	}

	@Test
	public void twoFlightEarliestFlightSameArrivalAndDeparture() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", c2.getTime(), c.getTime());
		Flight flight2 = new Flight(1, "SFO", "OAK", c2.getTime(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
	}

	@Test
	public void twoFlightEarliestFlightSameArrival() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", c2.getTime(), c.getTime());
		Flight flight2 = new Flight(2, "SFO", "OAK", new Date(), c.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
	}

	@Test
	public void twoFlightEarliestFlight() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "SFO", "OAK", new Date(), c2.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
		Assert.assertTrue(queue.peek().getFlightNumber() == 2);
	}

	@Test
	public void twoFlightEarliestFlightWithYear() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.YEAR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "SFO", "OAK", new Date(), c2.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
		Assert.assertTrue(queue.peek().getFlightNumber() == 2);
	}

	@Test
	public void twoFlightEarliestFlightCycle() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.YEAR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "OAK", "SFO", new Date(), c2.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "SFO");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 0);
	}

	@Test
	public void twoFlightEarliestFlightCycleOneWay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 4);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.YEAR, 2);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "OAK", "SFO", new Date(), c2.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "OAK");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
		Assert.assertTrue(queue.peek().getFlightNumber() == 1);
	}

	@Test
	public void fourFlightEarliestFlight() {
		//SFo -> CMH-> IAD is invalid because of the layover constraint
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Calendar c3 = Calendar.getInstance();
		c3.add(Calendar.HOUR, 3);
		Calendar c4 = Calendar.getInstance();
		c4.add(Calendar.HOUR, 4);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "SFO", "CMH", new Date(), c2.getTime());
		Flight flight3 = new Flight(2, "CMH", "IAD", c2.getTime(), c3.getTime());
		Flight flight4 = new Flight(2, "SFO", "IAD", new Date(), c4.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
		flightList.add(flight4);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "IAD");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
		Assert.assertTrue(queue.peek().getArrivalTime().equals(c4.getTime()));
	}

	@Test
	public void fourFlightEarliestFlightWithEnoughLayover() {
		//SFo -> CMH-> IAD is invalid because of the layover constraint
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Calendar c3 = Calendar.getInstance();
		c3.add(Calendar.HOUR, 3);
		Calendar c4 = Calendar.getInstance();
		c4.add(Calendar.HOUR, 4);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		Flight flight2 = new Flight(2, "SFO", "CMH", new Date(), c2.getTime());
		c2.add(Calendar.MINUTE, 40);
		Flight flight3 = new Flight(2, "CMH", "IAD", c2.getTime(), c3.getTime());
		Flight flight4 = new Flight(2, "SFO", "IAD", new Date(), c4.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
		flightList.add(flight4);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "IAD");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 2);
		c2.add(Calendar.MINUTE, -40);
		Assert.assertTrue(queue.peek().getArrivalTime().equals(c2.getTime()));
	}

	@Test
	public void fourFlightEarliestFlightWithEnoughLayoverDeadEnd() {
		//SFo -> CMH-> IAD is invalid because of the layover constraint
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Calendar c3 = Calendar.getInstance();
		c3.add(Calendar.HOUR, 3);
		Calendar c4 = Calendar.getInstance();
		c4.add(Calendar.HOUR, 4);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		c.add(Calendar.MINUTE, 30);
		Flight flight2 = new Flight(2, "OAK", "VEG", c.getTime(), c2.getTime());
		c2.add(Calendar.MINUTE, 40);
		Flight flight3 = new Flight(2, "VEG", "OOP", c2.getTime(), c3.getTime());
		Flight flight4 = new Flight(2, "SFO", "IAD", new Date(), c4.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
		flightList.add(flight4);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "IAD");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 1);
		Assert.assertTrue(queue.peek().getArrivalTime().equals(c4.getTime()));
	}

	@Test
	public void fiveFlightEarliestFlightWithEnoughLayoverDeadEnd() {
		//SFo -> CMH-> IAD is invalid because of the layover constraint
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.HOUR, 2);
		Calendar c3 = Calendar.getInstance();
		c3.add(Calendar.HOUR, 3);
		Calendar c4 = Calendar.getInstance();
		c4.add(Calendar.HOUR, 10);
		Flight flight1 = new Flight(1, "SFO", "OAK", new Date(), c.getTime());
		c.add(Calendar.MINUTE, 30);
		Flight flight2 = new Flight(2, "OAK", "VEG", c.getTime(), c2.getTime());
		c2.add(Calendar.MINUTE, 40);
		Flight flight3 = new Flight(2, "VEG", "OOP", c2.getTime(), c3.getTime());
		c3.add(Calendar.MINUTE, 30);
		Flight flight4 = new Flight(2, "OOP", "IAD", c3.getTime(), c4.getTime());
		c4.add(Calendar.MINUTE, 10);
		Flight flight5 = new Flight(2, "SFO", "IAD", new Date(), c4.getTime());
		List<Flight> flightList = new ArrayList<>();
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
		flightList.add(flight4);
		flightList.add(flight5);
		ShortestFlight shortestFlight = new ShortestFlight(new FlightsAvailable(flightList),"SFO", "IAD");
		Queue<Flight> queue = shortestFlight.shortestPathHelper();

		Assert.assertTrue(queue.size() == 4);
		c.add(Calendar.MINUTE, -30);
		Assert.assertTrue(queue.peek().getArrivalTime().equals(c.getTime()));
	}

}
