package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.shared.components.Schemas;

public record ProductResponse(
        Long id,
        String name,
        Schemas.Money price,
        String description,
        Integer stockQuantity,
        CategorySummary category,
        String imageUrl
) {

}
