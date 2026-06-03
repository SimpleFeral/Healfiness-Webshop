package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;
import com.healfiness.backend.shared.components.Schemas;

public record OrderSummary(
        Long id,
        OrderStatus status,
        Schemas.Money totalAmount
) {
}
