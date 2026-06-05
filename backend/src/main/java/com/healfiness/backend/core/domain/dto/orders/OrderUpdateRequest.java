package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;
import com.healfiness.backend.shared.components.Schemas;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderUpdateRequest(

        OrderStatus status,

        @Size(min = 1, message = "Order must contain at least one item")
        List<OrderItemCreateRequest> items,

        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity,

        @Min(value = 0, message = "Total amount must be non-negative")
        Schemas.Money totalAmount
) {
}
