package com.airline.data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yash on 5/7/17.
 */
public class Airport {
    private Queue<Flight> flightQueue;
    private long time;
    private String airportName;

    public Airport(String airportName) {
        this.time = 0L;
        this.flightQueue = new LinkedList<>();
        this.airportName = airportName;
    }

    public Queue<Flight> getFlightQueue() {
        return flightQueue;
    }

    public void setFlightQueue(Queue<Flight> flightQueue) {
        this.flightQueue = new LinkedList<>();
        flightQueue.forEach(f -> this.flightQueue.add(f));
    }

    public void updateAirportInformation(long currentFlightTime, Queue<Flight> existingFlightQueue, Flight flight) {
        this.setTime(currentFlightTime);
        this.setFlightQueue(existingFlightQueue);
        this.getFlightQueue().add(flight);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
