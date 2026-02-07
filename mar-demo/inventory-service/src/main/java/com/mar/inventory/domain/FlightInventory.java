package com.mar.inventory.domain;

public class FlightInventory {

    private final String flightId;
    private final int totalSeats;
    private int reservedSeats;

    public FlightInventory(String flightId, int totalSeats) {
        this.flightId = flightId;
        this.totalSeats = totalSeats;
        this.reservedSeats = 0;
    }

    public String getFlightId() {
        return flightId;
    }

    public int getAvailableSeats() {
        return totalSeats - reservedSeats;
    }

    public synchronized boolean reserveSeats(int count) {
        if (getAvailableSeats() < count) {
            return false;
        }
        reservedSeats += count;
        return true;
    }
}
