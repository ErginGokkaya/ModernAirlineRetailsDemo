package com.mar.offer.api;

import com.mar.offer.api.dto.OfferResponse;
import com.mar.offer.service.OfferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    @GetMapping
    public OfferResponse getOffer(
            @RequestParam("flightId") String flightId,
            @RequestParam("bundle") String bundle,
            @RequestParam("seats") int seats) {

        return service.createOffer(flightId, bundle, seats);
    }
}
