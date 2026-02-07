package com.mar.offer.api.dto;

public class OfferResponse {

    private String offerId;
    private String flightId;
    private String bundle;
    private int seats;
    private double totalPrice;

    public OfferResponse(String offerId, String flightId, String bundle, int seats, double totalPrice) {
        this.offerId = offerId;
        this.flightId = flightId;
        this.bundle = bundle;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public String getOfferId() { return offerId; }
    public String getFlightId() { return flightId; }
    public String getBundle() { return bundle; }
    public int getSeats() { return seats; }
    public double getTotalPrice() { return totalPrice; }
}
