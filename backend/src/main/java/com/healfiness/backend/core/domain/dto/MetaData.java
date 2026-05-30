package com.healfiness.backend.core.domain.dto;

import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.media.Schema;

public record MetaData(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Schemas.UserName createUser,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Schemas.Timestamp createDate,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Schemas.UserName lastModifiedUser,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Schemas.Timestamp lastModifiedDate,

        @Schema(type = "integer", format = "int64", example = "1",  accessMode = Schema.AccessMode.READ_ONLY)
        Long version
) {
}
