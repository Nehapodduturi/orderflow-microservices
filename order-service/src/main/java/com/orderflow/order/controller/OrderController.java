package com.orderflow.order.controller;

import com.orderflow.order.dto.CreateOrderRequest;
import com.orderflow.order.model.CustomerOrder;
import com.orderflow.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<CustomerOrder> create(@Valid @RequestBody CreateOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public CustomerOrder get(@PathVariable Long id){ return service.get(id); }

    @GetMapping("/user/{userId}")
    public List<CustomerOrder> byUser(@PathVariable Long userId){ return service.byUser(userId); }
}
