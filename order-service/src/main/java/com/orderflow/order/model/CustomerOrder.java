package com.orderflow.order.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private Long userId;
    @Column(nullable=false) private String sku;
    @Column(nullable=false) private Integer quantity;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private OrderStatus status = OrderStatus.CREATED;
    @Column(nullable=false) private Instant createdAt = Instant.now();

    public Long getId(){ return id; }
    public Long getUserId(){ return userId; }
    public void setUserId(Long userId){ this.userId = userId; }
    public String getSku(){ return sku; }
    public void setSku(String sku){ this.sku = sku; }
    public Integer getQuantity(){ return quantity; }
    public void setQuantity(Integer quantity){ this.quantity = quantity; }
    public OrderStatus getStatus(){ return status; }
    public void setStatus(OrderStatus status){ this.status = status; }
    public Instant getCreatedAt(){ return createdAt; }
}
