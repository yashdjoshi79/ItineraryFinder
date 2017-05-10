package com.airline.data;

import java.util.List;

/**
 * Created by yash on 5/7/17.
 */
public class FlightsAvailable {
    List<Flight> flights;

    public FlightsAvailable(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
