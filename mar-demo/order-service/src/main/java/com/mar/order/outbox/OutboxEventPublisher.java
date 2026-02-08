package com.mar.order.outbox;

import com.mar.common.events.topics.KafkaTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OutboxEventPublisher {

    private final OutboxEventRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxEventPublisher(OutboxEventRepository repository,
                                KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void publishEvents() {

        List<OutboxEvent> events = repository.findAll();

        for (OutboxEvent event : events) {
            kafkaTemplate.send(KafkaTopics.ORDER_CREATED, event.getAggregateId(), event.getPayload());
            repository.delete(event);
        }
    }
}
