package com.healfiness.backend.core.domain.dto.products;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String description,
        Integer stockQuantity,
        CategorySummary category,
        String imageUrl
) {

}
