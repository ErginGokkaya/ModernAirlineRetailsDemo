package com.mar.pricing.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public double calculatePrice(String bundleType, int seatCount) {
        double baseFare = 100.0;

        if ("COMFORT".equalsIgnoreCase(bundleType)) {
            baseFare += 40.0;
        }

        double total = baseFare * seatCount;

        if (seatCount > 3) {
            total = total * 0.9; // %10 discount
        }

        return total;
    }
}
