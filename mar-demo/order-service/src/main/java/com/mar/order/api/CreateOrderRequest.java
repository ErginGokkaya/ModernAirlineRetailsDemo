package com.mar.order.api;

public record CreateOrderRequest(
        String offerId,
        String flightId,
        String bundle,
        int seats,
        double totalPrice
) {}
