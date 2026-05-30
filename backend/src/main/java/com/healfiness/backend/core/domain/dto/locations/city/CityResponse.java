package com.healfiness.backend.core.domain.dto.locations.city;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.core.domain.dto.locations.isoCountryCode.IsoCountryCodeResponse;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record CityResponse(
        Schemas.Id citiesId,

        @Schema(type = "string", minLength = 2, maxLength = 3, example = "HAM",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String shortName,

        @Schema(type = "string", example = "Hamburg", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        IsoCountryCodeResponse isoCountryCode,

        MetaData metaData
) {
}
