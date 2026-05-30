package com.healfiness.backend.core.domain.dto.page;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Meta data of the page request")
public record PageMetaData(
        @Schema(type = "integer", example = "0")
        Integer page,

        @Schema(type = "integer", example = "20")
        Integer size,

        @Schema(type = "integer", format = "int64", example = "1")
        Long totalElements,

        @Schema(type = "integer", example = "1")
        Integer totalPages,

        @Schema(type = "boolean", example = "true")
        boolean last,

        List<SortOrder> sortOrders
) {
}
