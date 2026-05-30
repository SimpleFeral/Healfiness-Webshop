package com.healfiness.backend.core.domain.dto.products;

import java.util.List;

public record CategoryCreateRequest(
        String name,
        String description,
        List<ProductCreateRequest> products
) {
}
