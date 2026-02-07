package com.mar.common.events.order;

import com.mar.common.events.BaseEvent;

public class OrderCancelledEvent extends BaseEvent {

    private String orderId;
    private String reason;

    public OrderCancelledEvent() {}

    public OrderCancelledEvent(String orderId, String reason) {
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
