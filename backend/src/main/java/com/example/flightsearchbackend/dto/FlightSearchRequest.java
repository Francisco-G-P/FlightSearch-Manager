package com.example.flightsearchbackend.dto;

public class FlightSearchRequest {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int adults;
    private String currency;
    private boolean nonStop;

    public FlightSearchRequest() {
    }

    public FlightSearchRequest(String origin, String destination, String departureDate, String returnDate, int adults,
            String currency, boolean nonStop) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
        this.currency = currency;
        this.nonStop = nonStop;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isNonStop() {
        return nonStop;
    }

    public void setNonStop(boolean nonStop) {
        this.nonStop = nonStop;
    }
}
