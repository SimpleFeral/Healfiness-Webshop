package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProductSummary(
        Schemas.Id productsId,

        @Schema(type = "integer", minLength = 2, maxLength = 150,
                example = "Protein Powder", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Schemas.Money price
) {

}
