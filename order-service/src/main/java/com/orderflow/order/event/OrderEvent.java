package com.orderflow.order.event;

public record OrderEvent(Long orderId, Long userId, String sku, int quantity, String status) {}
