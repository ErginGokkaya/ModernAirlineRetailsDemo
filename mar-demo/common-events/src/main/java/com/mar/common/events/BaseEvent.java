package com.mar.common.events;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEvent {

    private final String eventId;
    private final Instant occurredAt;

    protected BaseEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = Instant.now();
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
