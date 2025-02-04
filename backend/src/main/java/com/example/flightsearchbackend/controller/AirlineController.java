package com.example.flightsearchbackend.controller;

import com.example.flightsearchbackend.service.AirlineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirlineController {

    @Autowired
    private AirlineInfoService airlineInfoService;

    @GetMapping("/airlines")
    public Map<String, Object> getAirline(@RequestParam String code) {
        return airlineInfoService.getAirlineInfo(code);
    }
}
