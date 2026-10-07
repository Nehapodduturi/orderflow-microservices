package com.orderflow.order.service;

import com.orderflow.order.dto.CreateOrderRequest;
import com.orderflow.order.event.OrderEvent;
import com.orderflow.order.model.*;
import com.orderflow.order.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final KafkaTemplate<String,Object> kafka;
    private final RestClient inventoryClient;

    public OrderService(OrderRepository repository, KafkaTemplate<String,Object> kafka, RestClient.Builder builder) {
        this.repository = repository;
        this.kafka = kafka;
        this.inventoryClient = builder.baseUrl("http://localhost:8082").build();
    }

    public CustomerOrder create(CreateOrderRequest request) {
        inventoryClient.post()
                .uri("/api/inventory/{sku}/reserve?quantity={quantity}", request.sku(), request.quantity())
                .retrieve().toBodilessEntity();

        CustomerOrder order = new CustomerOrder();
        order.setUserId(request.userId());
        order.setSku(request.sku());
        order.setQuantity(request.quantity());
        order.setStatus(OrderStatus.CONFIRMED);
        order = repository.save(order);

        kafka.send("order-events", order.getId().toString(),
                new OrderEvent(order.getId(), order.getUserId(), order.getSku(), order.getQuantity(), order.getStatus().name()));
        return order;
    }

    public CustomerOrder get(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<CustomerOrder> byUser(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
