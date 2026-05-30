package com.healfiness.backend.core.domain.dto.locations.city;

public record CityCreateRequest(
        String name,
        String shortName,
        Long isoCountryCodeId
) {
}
