package com.mar.inventory.service;

import com.mar.inventory.domain.FlightInventory;
import com.mar.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public int getAvailableSeats(String flightId) {
        FlightInventory inventory = repository.findByFlightId(flightId);
        if (inventory == null) {
            throw new IllegalArgumentException("Flight not found: " + flightId);
        }
        return inventory.getAvailableSeats();
    }

    public boolean reserveSeats(String flightId, int count) {
        FlightInventory inventory = repository.findByFlightId(flightId);
        if (inventory == null) {
            throw new IllegalArgumentException("Flight not found: " + flightId);
        }
        return inventory.reserveSeats(count);
    }
}
