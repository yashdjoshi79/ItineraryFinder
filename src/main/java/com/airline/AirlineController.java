package com.airline;

import com.airline.data.Flight;
import com.airline.data.FlightsAvailable;
import com.airline.service.ShortestFlight;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by yash on 5/7/17.
 */
@RestController
public class AirlineController {
    @RequestMapping("/computeFastestFlight")
    String fastestFlight(@RequestParam(value="flightData", required=true, defaultValue="") String flightData,
                         @RequestParam(value="origin", required = true, defaultValue = "") String origin,
                         @RequestParam(value = "destination", required = true,defaultValue = "") String destination) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            FlightsAvailable flightsAvailable = new FlightsAvailable(mapper.readValue(flightData, new TypeReference<List<Flight>>(){}));
            ShortestFlight shortestFlight = new ShortestFlight(flightsAvailable, origin, destination);
            return shortestFlight.findShortestFlight();
        } catch (IOException e) {
            e.printStackTrace();
            return "An Unexpected error occurred in reading the flight Data that was provided";
        }
    }
}
