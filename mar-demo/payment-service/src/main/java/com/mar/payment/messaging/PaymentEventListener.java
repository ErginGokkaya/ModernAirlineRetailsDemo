package com.mar.payment.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mar.common.events.order.OrderCreatedEvent;
import com.mar.common.events.payment.PaymentCompletedEvent;
import com.mar.common.events.payment.PaymentFailedEvent;
import com.mar.common.events.topics.KafkaTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentEventListener {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public PaymentEventListener(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = KafkaTopics.ORDER_CREATED, groupId = "payment-service")
    public void handleOrderCreated(String payload) throws Exception {

        OrderCreatedEvent event = objectMapper.readValue(payload, OrderCreatedEvent.class);
        System.out.println(">>> RAW MESSAGE: " + payload);
        boolean success = random.nextBoolean(); // ödeme simülasyonu

        if (success) {
            PaymentCompletedEvent completed = new PaymentCompletedEvent(
                    event.getOrderId(),
                    "TX-" + System.currentTimeMillis()
            );

            kafkaTemplate.send(
                    KafkaTopics.PAYMENT_COMPLETED,
                    event.getOrderId(),
                    objectMapper.writeValueAsString(completed)
            );
            System.out.println(">>> Parsed orderId PAYMENT_COMPLETED = " + event.getOrderId());
        } else {
            PaymentFailedEvent failed = new PaymentFailedEvent(
                    event.getOrderId(),
                    "Card declined"
            );

            kafkaTemplate.send(
                    KafkaTopics.PAYMENT_FAILED,
                    event.getOrderId(),
                    objectMapper.writeValueAsString(failed)
            );
            System.out.println(">>> Parsed orderId PAYMENT_FAILED = " + event.getOrderId());
        }
    }
}
