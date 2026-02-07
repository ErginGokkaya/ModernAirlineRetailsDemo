package com.mar.inventory.repository;

import com.mar.inventory.domain.FlightInventory;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InventoryRepository {

    private final Map<String, FlightInventory> storage = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        storage.put("FL-100", new FlightInventory("FL-100", 100));
        storage.put("FL-200", new FlightInventory("FL-200", 50));
    }

    public FlightInventory findByFlightId(String flightId) {
        return storage.get(flightId);
    }
}
