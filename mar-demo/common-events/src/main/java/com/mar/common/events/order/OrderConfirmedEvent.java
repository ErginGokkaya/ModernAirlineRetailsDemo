package com.mar.common.events.order;

import com.mar.common.events.BaseEvent;

public class OrderConfirmedEvent extends BaseEvent {

    private String orderId;

    public OrderConfirmedEvent() {}

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
