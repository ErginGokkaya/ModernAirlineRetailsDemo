package com.mar.offer.service;

import com.mar.offer.api.dto.OfferResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OfferService {

    private final RestTemplate restTemplate;

    @Value("${services.inventory.url}")
    private String inventoryUrl;

    @Value("${services.pricing.url}")
    private String pricingUrl;

    public OfferService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OfferResponse createOffer(String flightId, String bundle, int seats) {

        Integer available = restTemplate.getForObject(
                inventoryUrl + "/inventory/" + flightId, Integer.class);

        if (available == null || available < seats) {
            throw new IllegalStateException("Not enough seats available");
        }

        Double price = restTemplate.getForObject(
                pricingUrl + "/pricing/calculate?bundle=" + bundle + "&seats=" + seats,
                Double.class);

        return new OfferResponse(
                UUID.randomUUID().toString(),
                flightId,
                bundle,
                seats,
                price
        );
    }
}
