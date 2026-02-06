# 🗒️ MAR Demo – Architecture Notes

This document captures **key architectural and technical decisions** for the Modern Airline Retailing (MAR) demo project. These notes ensure important constraints and simplifications are not lost between development sessions.

---

## 🚪 Port Strategy

Each microservice runs on a fixed, dedicated port to avoid conflicts and simplify local development.

| Service | Port |
|--------|------|
| Offer Service | 8081 |
| Pricing Service | 8082 |
| Inventory Service | 8083 |
| Order Service | 8084 |
| Payment Service | 8085 |
| Order Query Service | 8086 |

---

## 🗄️ Database Strategy

We use **a single PostgreSQL container** for simplicity, but each service owns a separate schema to preserve microservice boundaries.

| Service | Schema Name |
|--------|-------------|
| Offer Service | `offer_db` |
| Pricing Service | `pricing_db` |
| Inventory Service | `inventory_db` |
| Order Service | `order_db` |
| Payment Service | `payment_db` |

### Notes
- Services must **only access their own schema**
- Cross-service DB access is strictly forbidden

---

## 🍃 MongoDB Usage

MongoDB is used **only** for the CQRS read model.

| Service | Purpose |
|---------|---------|
| Order Query Service | Read-optimized Order view |

---

## 📬 Messaging Decisions

| Area | Decision |
|------|----------|
| Broker | Apache Kafka |
| Message Format | JSON |
| Schema Registry | Not used (kept simple for demo) |

Kafka is used for:
- Saga communication
- Order state changes
- Read model updates

---

## 🔁 Saga Pattern

We use **Orchestration-based Saga**.

| Role | Service |
|------|---------|
| Saga Orchestrator | Order Service |
| Participants | Payment, Inventory (future), others |

Order Service controls the workflow and publishes commands/events.

---

## 🌐 Service Communication Style

| Communication Type | Used Between |
|--------------------|--------------|
| REST (sync) | Offer → Pricing, Offer → Inventory |
| Kafka (async) | Order ↔ Payment, Order → Query Service |

---

## 📦 Common Module Rules (`common-events`)

This module contains only **shared event contracts**.

### Allowed
- Event DTOs
- Topic name constants
- Shared enums (e.g., `OrderStatus`)

### Not Allowed
- JPA entities
- Business logic
- Service classes

---

## 🧱 Build & Repository Structure

- **Monorepo**
- **Multi-module Maven project**
- Each microservice is a separate Spring Boot module
- Services run independently at runtime

---

## 🧪 Intentional Simplifications (Future Work)

These are **deliberately excluded** to keep the demo manageable.

| Not Included | Reason |
|-------------|--------|
| Service Discovery (Eureka) | Adds infrastructure complexity |
| Central Config Server | Not needed for demo scale |
| API Gateway | Direct service calls are sufficient |
| Authentication / Authorization (JWT, OAuth2.0)| Focus is architecture, not security |
| Avro / Schema Registry | JSON is easier for learning/demo |

These components can be added later without breaking the architecture.

---

## 🎯 Architectural Summary

- Multi-module Maven monorepo  
- Spring Boot microservices  
- Single PostgreSQL, multi-schema isolation  
- MongoDB for CQRS read model  
- Kafka with JSON events  
- Saga orchestration handled by Order Service  
- Hybrid communication: REST + Event-driven  

---

**This document acts as a lightweight architecture decision record (ADR) for the MAR demo project.**
