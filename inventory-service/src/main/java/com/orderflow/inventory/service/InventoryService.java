package com.orderflow.inventory.service;

import com.orderflow.inventory.dto.InventoryRequest;
import com.orderflow.inventory.model.Product;
import com.orderflow.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final ProductRepository repository;

    public InventoryService(ProductRepository repository) { this.repository = repository; }

    public Product create(InventoryRequest request) {
        Product p = new Product();
        p.setSku(request.sku());
        p.setName(request.name());
        p.setQuantity(request.quantity());
        return repository.save(p);
    }

    public Product get(String sku) {
        return repository.findBySku(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Product reserve(String sku, int quantity) {
        Product p = get(sku);
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (p.getQuantity() < quantity) throw new IllegalArgumentException("Insufficient stock");
        p.setQuantity(p.getQuantity() - quantity);
        return repository.save(p);
    }
}
