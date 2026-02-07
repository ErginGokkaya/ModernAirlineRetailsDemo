package com.mar.common.events.topics;

public final class KafkaTopics {

    private KafkaTopics() {}

    public static final String ORDER_CREATED = "order-created";
    public static final String PAYMENT_REQUESTED = "payment-requested";
    public static final String PAYMENT_COMPLETED = "payment-completed";
    public static final String PAYMENT_FAILED = "payment-failed";
    public static final String ORDER_CONFIRMED = "order-confirmed";
    public static final String ORDER_CANCELLED = "order-cancelled";
}
