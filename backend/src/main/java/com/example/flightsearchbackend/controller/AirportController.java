package com.example.flightsearchbackend.controller;

import com.example.flightsearchbackend.service.AmadeusAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirportController {

    @Autowired
    private AmadeusAirportService amadeusAirportService;

    @GetMapping("/airports")
    public List<Map<String, Object>> getAirports(@RequestParam String query) {
        return amadeusAirportService.searchAirports(query);
    }
}
