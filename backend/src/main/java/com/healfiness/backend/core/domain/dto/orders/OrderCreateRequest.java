package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;

import java.util.List;

public record OrderCreateRequest(
        Long userId,
        List<OrderItemCreateRequest> orderItems,
        OrderStatus status,
        Integer quantity,
        Double totalAmount
) {
}
