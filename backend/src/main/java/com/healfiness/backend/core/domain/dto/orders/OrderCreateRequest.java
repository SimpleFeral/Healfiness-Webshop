package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;
import com.healfiness.backend.shared.components.Schemas;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequest(

        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotEmpty(message = "Order must contain at least one item")
        List<OrderItemCreateRequest> orderItems,

        @NotNull(message = "Order status cannot be null")
        OrderStatus status,

        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity,

        @Min(value = 0, message = "Total amount must be non-negative")
        Schemas.Money totalAmount
) {
}
