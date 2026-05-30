package com.healfiness.backend.core.domain.dto.shoppingcarts;

public record CartItemCreateRequest(
        Long productId,
        Integer quantity
) {
}
