package com.healfiness.backend.core.domain.dto.locations.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CityCreateRequest(

        @NotBlank(message = "Blank city name detected. Please provide a valid city name.")
        String name,

        @NotBlank(message = "Blank city short name detected. Please provide a valid city short name.")
        @Size(min = 3, max = 3, message = "City short name must be exactly 3 characters long. Please provide a valid city short name.")
        String shortName,

        @NotBlank(message = "ISO country code ID is required. Please provide a valid ISO country code ID.")
        Long isoCountryCodeId
) {
}
