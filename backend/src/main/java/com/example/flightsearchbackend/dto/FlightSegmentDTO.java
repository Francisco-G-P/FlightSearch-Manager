package com.example.flightsearchbackend.dto;

public class FlightSegmentDTO {
    private String departureDateTime;
    private String arrivalDateTime;
    private String departureAirport;
    private String departureAirportCode;
    private String arrivalAirport;
    private String arrivalAirportCode;
    private String airlineName;
    private String airlineCode;
    private String flightNumber;
    private String operatingAirlineName;
    private String operatingAirlineCode;
    private String aircraftType;
    private String layoverDuration;

    public FlightSegmentDTO(String departureDateTime, String arrivalDateTime, String departureAirport,
            String departureAirportCode, String arrivalAirport, String arrivalAirportCode,
            String airlineName, String airlineCode, String flightNumber,
            String operatingAirlineName, String operatingAirlineCode, String aircraftType,
            String layoverDuration) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.departureAirport = departureAirport;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirport = arrivalAirport;
        this.arrivalAirportCode = arrivalAirportCode;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.operatingAirlineName = operatingAirlineName;
        this.operatingAirlineCode = operatingAirlineCode;
        this.aircraftType = aircraftType;
        this.layoverDuration = layoverDuration;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getLayoverDuration() {
        return layoverDuration;
    }

    public void setLayoverDuration(String layoverDuration) {
        this.layoverDuration = layoverDuration;
    }
}
