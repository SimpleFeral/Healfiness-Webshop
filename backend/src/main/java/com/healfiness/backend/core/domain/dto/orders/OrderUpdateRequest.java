package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;

import java.util.List;

public record OrderUpdateRequest(
        OrderStatus status,
        List<OrderItemCreateRequest> items,
        Integer quantity,
        Double totalAmount
) {
}
