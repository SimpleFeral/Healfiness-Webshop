package com.healfiness.backend.core.domain.dto.shoppingcarts;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ShoppingCartResponse(
        Schemas.Id shoppingCartsId,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        List<CartItemResponse> items,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Schemas.Money price,

        Schemas.Id userId,

        MetaData metaData
) {

}
