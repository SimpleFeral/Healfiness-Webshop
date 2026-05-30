package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.core.domain.dto.MetaData;

import java.util.List;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        List<ProductSummary> products,
        MetaData metaData
) {
}
