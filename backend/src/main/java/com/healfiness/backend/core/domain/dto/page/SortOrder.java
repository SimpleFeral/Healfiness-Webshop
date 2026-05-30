package com.healfiness.backend.core.domain.dto.page;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record SortOrder(
        @Schema(type = "string", example = "createUser")
        String property,
        
        @Schema(type = "string", example = "ASC", defaultValue = "ASC")
        Direction direction
) {
    public enum Direction {
        ASC,
        DESC;

        public static Direction getByValue(String value) {
            for (Direction d : Direction.values()) {
                if (d.name().equalsIgnoreCase(value)) {
                    return d;
                }
            }
            return ASC;
        }
    }
}
