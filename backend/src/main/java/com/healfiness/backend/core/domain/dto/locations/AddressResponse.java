package com.healfiness.backend.core.domain.dto.locations;

import com.healfiness.backend.core.domain.dto.locations.city.CityResponse;
import com.healfiness.backend.shared.AddressType;
import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddressResponse(
        Schemas.Id addressesId,

        @Schema(type = "string", maxLength = 255, example = "Main Street",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String street,

        @Schema(type = "string", maxLength = 20, example = "42A",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String houseNumber,

        @Schema(type = "string", maxLength = 20, example = "20095",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String postalCode,

        @Schema(type = "string", maxLength = 20, nullable = true)
        String suffix,

        @Schema(type = "string", example = "HOME", defaultValue = "HOME",
                requiredMode = Schema.RequiredMode.REQUIRED)
        AddressType type,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        CityResponse city,

        Schemas.Id userId,

        MetaData metaData
) {
}
