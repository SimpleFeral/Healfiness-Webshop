package com.healfiness.backend.core.domain.dto.shoppingcarts;

import java.util.List;

public record ShoppingCartCreateRequest(
    Long userId,
    List<CartItemCreateRequest> items
) {
}
