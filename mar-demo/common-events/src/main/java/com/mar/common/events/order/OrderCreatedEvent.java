package com.mar.common.events.order;

import com.mar.common.events.BaseEvent;

public class OrderCreatedEvent extends BaseEvent {

    private String orderId;
    private String offerId;
    private double amount;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(String orderId, String offerId, double amount) {
        this.orderId = orderId;
        this.offerId = offerId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOfferId() {
        return offerId;
    }

    public double getAmount() {
        return amount;
    }
}
