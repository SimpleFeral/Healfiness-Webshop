package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.core.domain.dto.products.ProductSummary;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record OrderItemResponse(
        Schemas.Id orderItemsId,

        @Schema(type = "integer", minimum = "1", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer quantity,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Schemas.Money singlePriceAtOrderTime,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        ProductSummary product
) {
}
