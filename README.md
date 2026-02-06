# ✈️ Modern Airline Retailing (MAR) Demo

A weekend-scale demo project showcasing **Modern Airline Retailing concepts (Offers & Orders)** built with **Java Spring Boot microservices**, **Kafka-based event-driven architecture**, **Saga orchestration**, **Outbox pattern**, and **CQRS**.

This project simulates how an airline could implement a simplified **Offer → Order → Payment → Fulfillment** flow using modern distributed system patterns.

---

## 🎯 Project Goals

This demo aims to illustrate:

- Offer creation instead of traditional fare filing  
- Order-based booking instead of PNR + Ticket + EMD  
- Event-driven microservices communication  
- Saga orchestration for distributed transactions  
- Outbox pattern for reliable event publishing  
- CQRS with separate write and read models  

---

## 🧱 High-Level Architecture

```
Client → API Gateway → Microservices

Core Services:
- Offer Service
- Pricing Service
- Inventory Service
- Order Service (Saga Orchestrator)
- Payment Service
- Order Query Service (CQRS Read Model)

Communication:
- REST (synchronous)
- Kafka (asynchronous events)
```

---

## 🧩 Microservices Overview

| Service | Responsibility | Database |
|---------|----------------|----------|
| **Offer Service** | Builds flight offers by combining pricing and availability | PostgreSQL |
| **Pricing Service** | Calculates dynamic prices for bundles | PostgreSQL |
| **Inventory Service** | Manages seat availability and reservations | PostgreSQL |
| **Order Service** | Converts Offers into Orders and runs Saga orchestration | PostgreSQL |
| **Payment Service** | Simulates payment processing | PostgreSQL |
| **Order Query Service** | CQRS read model for fast order queries | MongoDB |

---

## 🔄 Core Flow (Business Scenario)

1. User searches flights  
2. **Offer Service** builds flight bundles (Basic / Comfort)  
3. User selects an offer  
4. **Order Service** creates an Order  
5. Saga triggers **Payment Service**  
6. On success → Order confirmed  
7. On failure → Order cancelled & inventory released  

---

## 📬 Event-Driven Architecture (Kafka Topics)

| Topic | Published By | Purpose |
|------|---------------|---------|
| `offer-created` | Offer Service | New offer generated |
| `order-created` | Order Service | Order initialized |
| `payment-requested` | Order Service | Trigger payment |
| `payment-completed` | Payment Service | Payment successful |
| `payment-failed` | Payment Service | Payment failed |
| `order-confirmed` | Order Service | Saga success |
| `order-cancelled` | Order Service | Saga rollback |

---

## 🧠 Architectural Patterns Used

### ✅ Saga (Orchestration)
Order Service acts as the **Saga orchestrator** managing the distributed transaction across services.

### ✅ Outbox Pattern
Order Service stores domain events in an **outbox table** and publishes them to Kafka via a background processor.

### ✅ CQRS
Write model lives in **Order Service (PostgreSQL)**.  
Read model lives in **Order Query Service (MongoDB)**, updated via Kafka events.

---

## 🗺️ Development Roadmap

We will build the system step by step:

### Phase 1 — Core Foundations
- [ ] Create project structure (multi-module or multi-repo)
- [ ] Docker Compose for PostgreSQL + MongoDB + Kafka
- [ ] Common library for shared event models

### Phase 2 — Core Domain Services
- [ ] Inventory Service (seat availability)
- [ ] Pricing Service (dynamic pricing logic)
- [ ] Offer Service (offer aggregation)

### Phase 3 — Ordering Flow
- [ ] Order Service (Order entity + DB)
- [ ] Implement Offer → Order conversion
- [ ] Implement Outbox table

### Phase 4 — Payments & Saga
- [ ] Payment Service
- [ ] Kafka integration
- [ ] Saga orchestration inside Order Service
- [ ] Handle success & rollback scenarios

### Phase 5 — CQRS Read Model
- [ ] Order Query Service (MongoDB)
- [ ] Kafka consumer to build read model
- [ ] Query API for orders

### Phase 6 — Polish
- [ ] API Gateway (optional)
- [ ] OpenAPI docs
- [ ] Sample Postman collection
- [ ] Demo scenario script

---

## 🧪 Demo Scenario (End Goal)

We should be able to:

1. Call **GET /offers**
2. Receive bundled flight offers
3. Call **POST /orders** with an offer
4. Watch Saga trigger payment
5. See Order move to **CONFIRMED** or **CANCELLED**
6. Query final state via **Order Query Service**

---

## 🛠️ Tech Stack

- Java 21  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring Data MongoDB  
- Apache Kafka  
- PostgreSQL  
- MongoDB  
- Docker Compose  

---

## 🚀 Status

Project is under active development as a learning/demo system for **Modern Airline Retailing architecture**.
