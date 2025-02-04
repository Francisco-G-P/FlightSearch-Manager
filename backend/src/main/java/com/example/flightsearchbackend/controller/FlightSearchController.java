package com.example.flightsearchbackend.controller;

import com.example.flightsearchbackend.dto.FlightResponseDTO;
import com.example.flightsearchbackend.dto.FlightSearchRequest;
import com.example.flightsearchbackend.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    @PostMapping("/flights")
    public List<FlightResponseDTO> searchFlights(@RequestBody FlightSearchRequest request) {
        return flightSearchService.searchFlights(request);
    }
}
