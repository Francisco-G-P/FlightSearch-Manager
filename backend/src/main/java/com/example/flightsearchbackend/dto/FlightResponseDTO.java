package com.example.flightsearchbackend.dto;

import java.util.List;

public class FlightResponseDTO {
    private String departureDateTime;
    private String arrivalDateTime;
    private String departureAirport;
    private String departureAirportCode;
    private String arrivalAirport;
    private String arrivalAirportCode;
    private String airlineName;
    private String airlineCode;
    private String operatingAirlineName;
    private String operatingAirlineCode;
    private String totalFlightDuration;
    private List<FlightSegmentDTO> segments;
    private double totalPrice;
    private double pricePerTraveler;
    private String currency;
    private double basePrice;
    private double fees;

    public FlightResponseDTO(String departureDateTime, String arrivalDateTime, String departureAirport,
            String departureAirportCode, String arrivalAirport, String arrivalAirportCode,
            String airlineName, String airlineCode, String operatingAirlineName, String operatingAirlineCode,
            String totalFlightDuration, List<FlightSegmentDTO> segments, double totalPrice, double pricePerTraveler,
            double basePrice, double fees, String currency) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.departureAirport = departureAirport;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirport = arrivalAirport;
        this.arrivalAirportCode = arrivalAirportCode;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.operatingAirlineName = operatingAirlineName;
        this.operatingAirlineCode = operatingAirlineCode;
        this.totalFlightDuration = totalFlightDuration;
        this.segments = segments;
        this.totalPrice = totalPrice;
        this.pricePerTraveler = pricePerTraveler;
        this.basePrice = basePrice;
        this.fees = fees;
        this.currency = currency;
    }

    public List<FlightSegmentDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<FlightSegmentDTO> segments) {
        this.segments = segments;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getOperatingAirlineName() {
        return operatingAirlineName;
    }

    public void setOperatingAirlineName(String operatingAirlineName) {
        this.operatingAirlineName = operatingAirlineName;
    }

    public String getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    public void setOperatingAirlineCode(String operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    public String getTotalFlightDuration() {
        return totalFlightDuration;
    }

    public void setTotalFlightDuration(String totalFlightDuration) {
        this.totalFlightDuration = totalFlightDuration;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPricePerTraveler() {
        return pricePerTraveler;
    }

    public void setPricePerTraveler(double pricePerTraveler) {
        this.pricePerTraveler = pricePerTraveler;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
