package com.mar.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mar.common.events.order.OrderCreatedEvent;
import com.mar.order.domain.Order;
import com.mar.order.outbox.OutboxEvent;
import com.mar.order.outbox.OutboxEventRepository;
import com.mar.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository,
                        OutboxEventRepository outboxRepository,
                        ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Order createOrder(String offerId, String flightId, String bundle, int seats, double price) {

        Order order = new Order(offerId, flightId, bundle, seats, price);
        orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                offerId,
                price
        );

        try {
            String payload = objectMapper.writeValueAsString(event);

            OutboxEvent outboxEvent = new OutboxEvent(
                    "Order",
                    order.getId(),
                    "OrderCreated",
                    payload
            );

            outboxRepository.save(outboxEvent);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }

        return order;
    }
}

