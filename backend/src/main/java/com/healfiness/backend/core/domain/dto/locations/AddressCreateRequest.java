package com.healfiness.backend.core.domain.dto.locations;

import com.healfiness.backend.core.domain.dto.locations.city.CityCreateRequest;
import com.healfiness.backend.shared.AddressType;

public record AddressCreateRequest(

         String street,

         String houseNumber,

         String postalCode,

         String suffix,

         AddressType type,

         CityCreateRequest city
) {
}