package com.healfiness.backend.core.domain.dto.locations.isoCountryCode;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record IsoCountryCodeResponse(
        Schemas.Id isoCountryCodesId,

        @Schema(type = "string", minLength = 2, maxLength = 3,
                example = "DE", requiredMode = Schema.RequiredMode.REQUIRED)
        String isoCountryCode,

        @Schema(type = "string", example = "Germany", requiredMode = Schema.RequiredMode.REQUIRED)
        String countryName,

        MetaData metaData
) {
}
