package com.airline.service;

import com.airline.data.Airport;
import com.airline.data.Flight;
import com.airline.data.FlightsAvailable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yash on 5/7/17.
 */
public class ShortestFlight {
    private FlightsAvailable flightList;
    private String origin;
    private String destination;
    private int numberOfFlights;
    private Date timeArrived;
    private Set<String> deadendDestinations;
    private HashMap<String, Airport> airportMap;


    public ShortestFlight(FlightsAvailable flightsAvailable, String origin, String destination) {
        this.flightList = flightsAvailable;
        this.origin = origin;
        this.destination = destination;
        this.numberOfFlights = flightsAvailable.getFlights().size();
        this.airportMap = generateAirportList(flightsAvailable.getFlights());
        this.timeArrived = null;
        this.deadendDestinations = new HashSet<>();
    }

    public String findShortestFlight() {
        shortestPathHelper();
        return printAllInformation(airportMap.get(destination.toLowerCase()).getFlightQueue());
    }

    public Queue<Flight> shortestPathHelper() {
        String originAirport = origin;
        for(int i =0 ; i < numberOfFlights; i++) {
            List<Flight> flightsFromOrigin = getFlightsFromOrigin(originAirport);
            if(flightsFromOrigin.size() == 0) {
                flightsFromOrigin = moveToNextSibling(originAirport, flightsFromOrigin);
            }
            Flight flight = getShortestFlight(flightsFromOrigin, timeArrived, originAirport);
            if(flight == null) {
                continue;
            }
            if (flight.getDestinationAirport().equalsIgnoreCase(destination)) {
                break;
            }
            originAirport = flight.getDestinationAirport();
            timeArrived = flight.getArrivalTime();
        }
        Airport updatedAirport = airportMap.get(destination.toLowerCase());
        return  updatedAirport == null ? new LinkedList<>() : updatedAirport.getFlightQueue();
    }

    private List<Flight> moveToNextSibling(String originAirport, List<Flight> flightsFromOrigin) {
        deadendDestinations.add(originAirport);
        Airport currentAirport = airportMap.get(originAirport.toLowerCase());
        if(currentAirport == null) {
            return new LinkedList<>();
        }
        Flight previousFlight = currentAirport.getFlightQueue().peek();
        Airport previousAirport = airportMap.get(previousFlight.getSourceAirport().toLowerCase());
        originAirport = previousAirport.getAirportName();
        List<Flight> otherFlightsFromPrevious = getFlightsFromOrigin(originAirport);
        flightsFromOrigin = otherFlightsFromPrevious.stream()
                .filter(f -> !deadendDestinations.contains(f.getDestinationAirport()))
                .collect(Collectors.toList());
        Queue<Flight> previousFLightQueue = previousAirport.getFlightQueue();
        if(previousFLightQueue != null && !previousFLightQueue.isEmpty()) {
            Flight flightBeforePrevious = previousFLightQueue.peek();
            timeArrived = flightBeforePrevious.getArrivalTime();
        } else {
            timeArrived = null;
        }
        return flightsFromOrigin;
    }

    private String printAllInformation(Queue<Flight> flightsEvaluated) {
        String s = "";
        for(Flight flight : flightsEvaluated) {
            s = s + "<p>Board flight " + flight.getFlightNumber() + " to depart " + flight.getSourceAirport() + " at " +
                    flight.getDepartureTime() + " and arrive at " + flight.getDestinationAirport() + " at " +
                    flight.getArrivalTime() + "." + "</p>";
        }
        return s;
    }

    private List<Flight> getFlightsFromOrigin(String originAirport) {
        return flightList.getFlights()
                .stream()
                .filter(f -> f.getSourceAirport().equalsIgnoreCase(originAirport))
                .collect(Collectors.toList());
    }

    private Flight getShortestFlight(List<Flight> flights, Date timeArrived, String origin) {
        Airport originAirport = airportMap.get(origin.toLowerCase());
        long shortestTime = 0l;
        Flight shortestFlight = null;
        if(originAirport == null) {
            return null;
        }
        Queue<Flight> existingFlightQueue = originAirport.getFlightQueue();
        for(Flight flight : flights) {
            long currentFlightTime = flight.getArrivalTime().getTime();
            if(timeArrived != null) {
                long layover = flight.getDepartureTime().getTime() - timeArrived.getTime();
                if((layover /1000 * 60) <= 20) {
                    //Flight invalid
                    continue;
                }
            }
            Airport currentAirport = airportMap.get(flight.getDestinationAirport().toLowerCase());
            long currentBestTime = currentAirport.getTime();

            if(currentFlightTime < currentBestTime || currentBestTime == 0L) {
                currentAirport.updateAirportInformation(currentFlightTime, existingFlightQueue, flight);
            }

            if(shortestTime > currentAirport.getTime() || shortestTime == 0L) {
                shortestTime = currentAirport.getTime();
                shortestFlight = flight;
            }
        }
        return shortestFlight;
    }

    private HashMap<String, Airport> generateAirportList(List<Flight> flights) {
        HashMap<String, Airport> map = new HashMap<>();
        for(Flight f : flights) {
            map.computeIfAbsent(f.getSourceAirport().toLowerCase(), k -> new Airport(f.getSourceAirport().toLowerCase()));
            map.computeIfAbsent(f.getDestinationAirport().toLowerCase(), k -> new Airport(f.getDestinationAirport().toLowerCase()));
        }
        return map;
    }
}
