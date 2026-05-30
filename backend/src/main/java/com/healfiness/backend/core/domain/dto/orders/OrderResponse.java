package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.shared.OrderStatus;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record OrderResponse(
        Schemas.Id ordersId,

        @Schema(type = "string", example = "PENDING", defaultValue = "PENDING", requiredMode = Schema.RequiredMode.REQUIRED)
        OrderStatus status,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Schemas.Money totalAmount,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, accessMode = Schema.AccessMode.READ_ONLY)
        Schemas.Timestamp orderDate,

        @Schema(type = "integer", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer totalQuantity,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        List<OrderItemResponse> orderItems,

        Schemas.Id userId
) {
}
