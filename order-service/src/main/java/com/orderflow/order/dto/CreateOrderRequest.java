package com.orderflow.order.dto;

import jakarta.validation.constraints.*;

public record CreateOrderRequest(@NotNull Long userId, @NotBlank String sku, @Min(1) int quantity) {}
