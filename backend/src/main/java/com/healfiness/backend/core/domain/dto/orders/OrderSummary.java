package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;

public record OrderSummary(
        Long id,
        OrderStatus status,
        Double totalAmount
) {
}
