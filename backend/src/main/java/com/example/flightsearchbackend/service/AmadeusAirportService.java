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
public class AmadeusAirportService {

    @Autowired
    private AmadeusConfig amadeusConfig;

    @Autowired
    private AmadeusService amadeusService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final Map<String, String> airportCache = new HashMap<>();

    public String getAirportName(String iataCode) {

        if (airportCache.containsKey(iataCode)) {
            return airportCache.get(iataCode);
        }

        String url = amadeusConfig.getApiBaseUrl() + "/v1/reference-data/locations?subType=AIRPORT&keyword=" + iataCode;

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
            return "Unknown Airport";
        }

        List<Map<String, Object>> airports = objectMapper.convertValue(
                responseBody.get("data"), new TypeReference<List<Map<String, Object>>>() {
                });

        if (airports != null && !airports.isEmpty()) {
            String airportName = (String) airports.get(0).get("name");
            airportCache.put(iataCode, airportName); // Store in cache
            return airportName;
        }
        return "Unknown Airport";
    }

    public List<Map<String, Object>> searchAirports(String query) {
        String url = amadeusConfig.getApiBaseUrl() + "/v1/reference-data/locations?subType=AIRPORT&keyword=" + query;

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
            throw new RuntimeException("No airports found.");
        }

        return objectMapper.convertValue(responseBody.get("data"), new TypeReference<List<Map<String, Object>>>() {
        });
    }
}
