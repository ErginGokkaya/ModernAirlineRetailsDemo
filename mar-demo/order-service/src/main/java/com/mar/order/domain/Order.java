package com.mar.order.domain;

import com.mar.common.events.enums.OrderStatus;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "order_db")
public class Order {

    @Id
    private String id;

    private String offerId;
    private String flightId;
    private String bundle;
    private int seats;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Instant createdAt;

    protected Order() {}

    public Order(String offerId, String flightId, String bundle, int seats, double totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.offerId = offerId;
        this.flightId = flightId;
        this.bundle = bundle;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.CREATED;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public OrderStatus getStatus() { return status; }

    public void markPaymentPending() {
        this.status = OrderStatus.PAYMENT_PENDING;
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }
}
