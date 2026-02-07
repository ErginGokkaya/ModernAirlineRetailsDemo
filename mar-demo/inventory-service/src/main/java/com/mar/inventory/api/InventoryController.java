package com.mar.inventory.api;

import com.mar.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Integer> getAvailability(@PathVariable("flightId") String flightId) {
        return ResponseEntity.ok(service.getAvailableSeats(flightId));
    }

    @PostMapping("/{flightId}/reserve")
    public ResponseEntity<String> reserve(
            @PathVariable("flightId") String flightId,
            @RequestParam("seats") int seats) {

        boolean success = service.reserveSeats(flightId, seats);

        if (!success) {
            return ResponseEntity.badRequest().body("Not enough seats available");
        }

        return ResponseEntity.ok("Seats reserved successfully");
    }
}
