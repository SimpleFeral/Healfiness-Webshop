package com.healfiness.backend.core.domain.dto.shoppingcarts;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.core.domain.dto.products.ProductSummary;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record CartItemResponse(
        Schemas.Id cartItemsId,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        ProductSummary product,

        @Schema(type = "integer", minimum = "1", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer quantity,

        MetaData metaData
) {
}
