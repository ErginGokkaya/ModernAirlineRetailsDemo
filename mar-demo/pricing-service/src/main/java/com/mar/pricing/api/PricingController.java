package com.mar.pricing.api;

import com.mar.pricing.service.PricingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/calculate")
    public double calculate(
            @RequestParam("bundle") String bundle,
            @RequestParam("seats") int seats) {

        return pricingService.calculatePrice(bundle, seats);
    }
}
