package com.orderflow.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryRequest(@NotBlank String sku, @NotBlank String name, @Min(0) int quantity) {}
