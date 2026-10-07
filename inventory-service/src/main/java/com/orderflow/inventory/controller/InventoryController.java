package com.orderflow.inventory.controller;

import com.orderflow.inventory.dto.InventoryRequest;
import com.orderflow.inventory.model.Product;
import com.orderflow.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody InventoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{sku}")
    public Product get(@PathVariable String sku) { return service.get(sku); }

    @PostMapping("/{sku}/reserve")
    public Product reserve(@PathVariable String sku, @RequestParam int quantity) {
        return service.reserve(sku, quantity);
    }
}
