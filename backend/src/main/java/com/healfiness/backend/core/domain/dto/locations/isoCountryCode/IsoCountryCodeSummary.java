package com.healfiness.backend.core.domain.dto.locations.isoCountryCode;

public record IsoCountryCodeSummary(
        Long isoCountryCodesId,
        String isoCountryCode,
        String countryName
) {
}
