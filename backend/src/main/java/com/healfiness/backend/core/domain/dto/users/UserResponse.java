package com.healfiness.backend.core.domain.dto.users;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import com.healfiness.backend.core.domain.dto.shoppingcarts.ShoppingCartResponse;
import com.healfiness.backend.shared.UserRoles;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema
public record UserResponse(
        Schemas.Id usersId,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Schemas.UserName username,

        @Schema(type = "string", format = "email", requiredMode = Schema.RequiredMode.REQUIRED, example = "john.doe@mail.com")
        String email,

        @Schema(type = "string", example = "John")
        String firstName,

        @Schema(type = "string", example = "Doe")
        String lastName,

        @Schema(type = "string", maxLength = 50, example = "+491234567890")
        String phoneNumber,

        @Schema(type = "string", example = "ADMIN", defaultValue = "GUEST")
        UserRoles role,

        List<AddressResponse> addresses,
        List<OrderResponse> orders,
        List<ShoppingCartResponse> shoppingCarts,

        MetaData metaData
) {

}
