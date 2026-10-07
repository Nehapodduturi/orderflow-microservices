# OrderFlow Microservices

OrderFlow is a Java/Spring Boot microservices project that demonstrates a realistic order-processing workflow using separate Order, Inventory, and Notification services.

## Architecture

Customer -> Order Service -> Inventory Service
                         -> Kafka -> Notification Service
Inventory Service -> PostgreSQL + Redis
Order Service -> PostgreSQL

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Redis
- Docker / Docker Compose
- Swagger / OpenAPI
- JUnit / Mockito
- Maven

## Services

### Order Service
- Create orders
- Retrieve orders
- Track order status
- Validate inventory before confirmation
- Publish order events to Kafka

### Inventory Service
- Create products
- Check product availability
- Reserve stock
- Redis-backed caching

### Notification Service
- Consume order events from Kafka
- Log/store notification activity

## Run

```bash
docker compose up --build
```

Default ports:
- Order Service: 8081
- Inventory Service: 8082
- Notification Service: 8083
- PostgreSQL: 5432
- Redis: 6379
- Kafka: 9092
