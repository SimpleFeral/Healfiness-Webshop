package com.healfiness.backend.core.domain.dto.products;

public record ProductCreateRequest(
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        Long categoryId
) {

}
