package com.healfiness.backend.core.domain.dto.orders;

public record OrderItemCreateRequest(
        Long productId,
        Integer quantity
) {
}
