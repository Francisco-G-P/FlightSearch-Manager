package com.example.flightsearchbackend.service;

import com.example.flightsearchbackend.config.AmadeusConfig;
import com.example.flightsearchbackend.dto.FlightResponseDTO;
import com.example.flightsearchbackend.dto.FlightSegmentDTO;
import com.example.flightsearchbackend.dto.FlightSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class FlightSearchService {

        @Autowired
        private AmadeusConfig amadeusConfig;

        @Autowired
        private AmadeusService amadeusService;

        @Autowired
        private AmadeusAirportService airportService;

        @Autowired
        private AirlineInfoService airlineService;

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private ObjectMapper objectMapper;

        private String calculateLayoverTime(String arrivalTime, String nextDepartureTime) {
                try {
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                        LocalDateTime arrival = LocalDateTime.parse(arrivalTime, formatter);
                        LocalDateTime nextDeparture = LocalDateTime.parse(nextDepartureTime, formatter);

                        Duration layoverDuration = Duration.between(arrival, nextDeparture);
                        long hours = layoverDuration.toHours();
                        long minutes = layoverDuration.toMinutes() % 60;

                        return String.format("%02d:%02d", hours, minutes);
                } catch (Exception e) {
                        return "N/A";
                }
        }

        private String formatDateTime(String isoDateTime) {
                try {
                        LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);
                        DateTimeFormatter formatter = DateTimeFormatter
                                        .ofPattern("MMMM d, yyyy - h:mm a", Locale.ENGLISH)
                                        .withZone(ZoneId.of("UTC"));
                        return dateTime.format(formatter);
                } catch (Exception e) {
                        return isoDateTime;
                }
        }

        public List<FlightResponseDTO> searchFlights(FlightSearchRequest request) {
                String url = amadeusConfig.getApiBaseUrl() + "/v2/shopping/flight-offers";

                String accessToken = amadeusService.getAccessToken();

                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + accessToken);
                headers.set("Content-Type", "application/json");

                String requestBody = "{"
                                + "\"currencyCode\":\"" + request.getCurrency() + "\","
                                + "\"originDestinations\":[{"
                                + "\"id\":\"1\","
                                + "\"originLocationCode\":\"" + request.getOrigin() + "\","
                                + "\"destinationLocationCode\":\"" + request.getDestination() + "\","
                                + "\"departureDateTimeRange\":{\"date\":\"" + request.getDepartureDate() + "\"}"
                                + "}"
                                + (request.getReturnDate() != null && !request.getReturnDate().isEmpty()
                                                ? ",{\"id\":\"2\",\"originLocationCode\":\"" + request.getDestination()
                                                                + "\","
                                                                + "\"destinationLocationCode\":\"" + request.getOrigin()
                                                                + "\","
                                                                + "\"departureDateTimeRange\":{\"date\":\""
                                                                + request.getReturnDate() + "\"}}"
                                                : "")
                                + "],"
                                + "\"travelers\":[{\"id\":\"1\",\"travelerType\":\"ADULT\"}],"
                                + "\"sources\":[\"GDS\"],"
                                + "\"searchCriteria\":{"
                                + "\"maxFlightOffers\":10,"
                                + "\"flightFilters\":{"
                                + "\"cabinRestrictions\":[{\"cabin\":\"ECONOMY\",\"coverage\":\"MOST_SEGMENTS\",\"originDestinationIds\":[\"1\"]}],"
                                + "\"carrierRestrictions\":{}},"
                                + "\"nonStop\":" + request.isNonStop()
                                + "}}";

                HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

                ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                                url,
                                HttpMethod.POST,
                                httpEntity,
                                new ParameterizedTypeReference<Map<String, Object>>() {
                                });

                Map<String, Object> responseBody = response.getBody();
                if (responseBody == null || !responseBody.containsKey("data")) {
                        throw new RuntimeException("No flights found.");
                }

                List<Map<String, Object>> flightOffers = objectMapper.convertValue(
                                responseBody.get("data"),
                                new TypeReference<List<Map<String, Object>>>() {
                                });

                List<FlightResponseDTO> flightResponses = new ArrayList<>();

                for (Map<String, Object> offer : flightOffers) {
                        List<Map<String, Object>> itineraries = objectMapper.convertValue(
                                        offer.get("itineraries"),
                                        new TypeReference<List<Map<String, Object>>>() {
                                        });

                        List<FlightSegmentDTO> segments = new ArrayList<>();
                        String previousArrivalTime = null;

                        for (Map<String, Object> itinerary : itineraries) {
                                List<Map<String, Object>> segmentsList = objectMapper.convertValue(
                                                itinerary.get("segments"),
                                                new TypeReference<List<Map<String, Object>>>() {
                                                });

                                for (Map<String, Object> segment : segmentsList) {
                                        String departureDateTimeIso = (String) objectMapper
                                                        .convertValue(segment.get("departure"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        })
                                                        .get("at");
                                        String arrivalDateTimeIso = (String) objectMapper
                                                        .convertValue(segment.get("arrival"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        })
                                                        .get("at");

                                        String departureDateTime = formatDateTime(departureDateTimeIso);
                                        String arrivalDateTime = formatDateTime(arrivalDateTimeIso);

                                        String departureAirportCode = (String) objectMapper
                                                        .convertValue(segment.get("departure"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        })
                                                        .get("iataCode");
                                        String arrivalAirportCode = (String) objectMapper
                                                        .convertValue(segment.get("arrival"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        })
                                                        .get("iataCode");

                                        String departureAirport = airportService.getAirportName(departureAirportCode);
                                        String arrivalAirport = airportService.getAirportName(arrivalAirportCode);

                                        String airlineCode = (String) segment.get("carrierCode");
                                        String flightNumber = (String) segment.get("number");

                                        String operatingAirlineCode = segment.containsKey("operating")
                                                        ? (String) objectMapper.convertValue(segment.get("operating"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        }).get("carrierCode")
                                                        : airlineCode;

                                        String airlineName = airlineService.getAirlineName(airlineCode);
                                        String operatingAirlineName = airlineService
                                                        .getAirlineName(operatingAirlineCode);

                                        String aircraftType = segment.containsKey("aircraft")
                                                        ? (String) objectMapper.convertValue(segment.get("aircraft"),
                                                                        new TypeReference<Map<String, Object>>() {
                                                                        }).get("code")
                                                        : "Unknown";

                                        String layoverDuration = null;
                                        if (previousArrivalTime != null) {
                                                layoverDuration = calculateLayoverTime(previousArrivalTime,
                                                                departureDateTime);
                                        }
                                        previousArrivalTime = arrivalDateTime;

                                        segments.add(new FlightSegmentDTO(
                                                        departureDateTime, arrivalDateTime,
                                                        departureAirport, departureAirportCode,
                                                        arrivalAirport, arrivalAirportCode,
                                                        airlineName, airlineCode,
                                                        flightNumber,
                                                        operatingAirlineName, operatingAirlineCode,
                                                        aircraftType, layoverDuration));
                                }
                        }

                        String totalFlightDurationIso = itineraries.get(0).get("duration").toString();
                        String totalFlightDuration = formatDuration(totalFlightDurationIso);

                        Map<String, Object> priceDetails = objectMapper.convertValue(
                                        offer.get("price"),
                                        new TypeReference<Map<String, Object>>() {
                                        });
                        double totalPrice = Double.parseDouble((String) priceDetails.get("total"));
                        double pricePerTraveler = totalPrice / request.getAdults();
                        String currency = (String) priceDetails.get("currency");

                        double basePrice = priceDetails.containsKey("base")
                                        ? Double.parseDouble((String) priceDetails.get("base"))
                                        : totalPrice;

                        double fees = totalPrice - basePrice;

                        basePrice = Math.round(basePrice * 100.0) / 100.0;
                        fees = Math.round(fees * 100.0) / 100.0;

                        flightResponses.add(new FlightResponseDTO(
                                        segments.get(0).getDepartureDateTime(),
                                        segments.get(segments.size() - 1).getArrivalDateTime(),
                                        segments.get(0).getDepartureAirport(),
                                        segments.get(0).getDepartureAirportCode(),
                                        segments.get(segments.size() - 1).getArrivalAirport(),
                                        segments.get(segments.size() - 1).getArrivalAirportCode(),
                                        segments.get(0).getAirlineName(), segments.get(0).getAirlineCode(),
                                        segments.get(0).getOperatingAirlineName(),
                                        segments.get(0).getOperatingAirlineCode(),
                                        totalFlightDuration, segments, totalPrice,
                                        pricePerTraveler, basePrice, fees, currency));
                }
                return flightResponses;
        }

        private String formatDuration(String isoDuration) {
                if (isoDuration == null || !isoDuration.startsWith("PT")) {
                        return "Unknown Duration";
                }

                isoDuration = isoDuration.substring(2);
                String hours = "0", minutes = "0";

                if (isoDuration.contains("H")) {
                        hours = isoDuration.split("H")[0];
                        isoDuration = isoDuration.contains("M") ? isoDuration.split("H")[1] : "";
                }

                if (isoDuration.contains("M")) {
                        minutes = isoDuration.replace("M", "");
                }

                return hours + "h " + minutes + "m";
        }
}
