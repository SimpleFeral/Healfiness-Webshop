package com.healfiness.backend.shared.components;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.Instant;

public final class Schemas {

    private Schemas() {
    }

    public record Id(
            @Schema(type = "integer", format = "int64", example = "123",
                    accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.REQUIRED)
            @Min(value = 1, message = "ID must be a positive integer")
            Long value
    ) {
    }

    public record Money(
            @Schema(type = "number", format = "double", example = "29.99")
            BigDecimal value
    ) {
    }

    public record Timestamp(
            @Schema(type = "string", format = "date-time", example = "2026-05-07T14:30:00Z")
            Instant value
    ) {
    }

    public record UserName(
            @Schema(type = "string", minLength = 3, maxLength = 15, example = "johndoe")
            String value
    ) {
    }

}