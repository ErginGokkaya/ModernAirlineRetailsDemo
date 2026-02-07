package com.mar.common.events.payment;

import com.mar.common.events.BaseEvent;

public class PaymentCompletedEvent extends BaseEvent {

    private String orderId;
    private String transactionId;

    public PaymentCompletedEvent() {}

    public PaymentCompletedEvent(String orderId, String transactionId) {
        this.orderId = orderId;
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
