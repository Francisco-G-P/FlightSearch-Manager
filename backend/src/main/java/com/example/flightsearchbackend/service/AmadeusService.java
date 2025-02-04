package com.example.flightsearchbackend.service;

import com.example.flightsearchbackend.config.AmadeusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AmadeusService {

    @Autowired
    private AmadeusConfig amadeusConfig;

    @Autowired
    private RestTemplate restTemplate;

    public String getAccessToken() {
        String url = amadeusConfig.getApiBaseUrl() + "/v1/security/oauth2/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=client_credentials&client_id=" + amadeusConfig.getApiKey() + "&client_secret="
                + amadeusConfig.getApiSecret();

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new RuntimeException("Failed to retrieve access token");
        }

        return (String) responseBody.get("access_token");
    }
}
