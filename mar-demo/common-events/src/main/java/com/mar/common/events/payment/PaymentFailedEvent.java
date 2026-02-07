package com.mar.common.events.payment;

import com.mar.common.events.BaseEvent;

public class PaymentFailedEvent extends BaseEvent {

    private String orderId;
    private String reason;

    public PaymentFailedEvent() {}

    public PaymentFailedEvent(String orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }
}
