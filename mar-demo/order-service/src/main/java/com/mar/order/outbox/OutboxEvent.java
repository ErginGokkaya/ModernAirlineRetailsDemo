package com.mar.order.outbox;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events", schema = "order_db")
public class OutboxEvent {

    @Id
    private String id;

    private String aggregateType;
    private String aggregateId;
    private String type;

    @Lob
    private String payload;

    private Instant createdAt;

    protected OutboxEvent() {}

    public OutboxEvent(String aggregateType, String aggregateId, String type, String payload) {
        this.id = UUID.randomUUID().toString();
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getPayload() { return payload; }
}
