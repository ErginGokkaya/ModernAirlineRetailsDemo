package com.mar.common.events.payment;

import com.mar.common.events.BaseEvent;

public class PaymentRequestedEvent extends BaseEvent {

    private String orderId;
    private double amount;

    public PaymentRequestedEvent() {}

    public PaymentRequestedEvent(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
}
