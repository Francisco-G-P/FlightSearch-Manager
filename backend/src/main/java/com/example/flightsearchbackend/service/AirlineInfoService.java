package com.example.flightsearchbackend.service;

import com.example.flightsearchbackend.config.AmadeusConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirlineInfoService {

    @Autowired
    private AmadeusConfig amadeusConfig;

    @Autowired
    private AmadeusService amadeusService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final Map<String, String> airlineCache = new HashMap<>();

    public String getAirlineName(String airlineCode) {

        if (airlineCache.containsKey(airlineCode)) {
            return airlineCache.get(airlineCode);
        }

        String url = amadeusConfig.getApiBaseUrl() + "/v1/reference-data/airlines?airlineCodes=" + airlineCode;

        String accessToken = amadeusService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("data")) {
            return "Unknown Airline";
        }

        List<Map<String, Object>> airlines = objectMapper.convertValue(
                responseBody.get("data"), new TypeReference<List<Map<String, Object>>>() {
                });

        if (airlines != null && !airlines.isEmpty()) {
            String airlineName = (String) airlines.get(0).get("businessName");
            airlineCache.put(airlineCode, airlineName); // Store in cache
            return airlineName;
        }
        return "Unknown Airline";
    }

    public Map<String, Object> getAirlineInfo(String airlineCode) {
        String url = amadeusConfig.getApiBaseUrl() + "/v1/reference-data/airlines?airlineCodes=" + airlineCode;

        String accessToken = amadeusService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        return response.getBody();
    }
}
