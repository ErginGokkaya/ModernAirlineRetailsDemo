package com.mar.order.api;

import com.mar.order.domain.Order;
import com.mar.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderRequest request) {
        return service.createOrder(
                request.offerId(),
                request.flightId(),
                request.bundle(),
                request.seats(),
                request.totalPrice()
        );
    }
}
