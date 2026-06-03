package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.shared.components.Schemas;

public record ProductCreateRequest(
        String name,
        String description,
        Schemas.Money price,
        Integer stockQuantity,
        Long categoryId
) {

}
