package com.healfiness.backend.core.domain.dto.locations;

import com.healfiness.backend.core.domain.dto.locations.city.CityCreateRequest;
import com.healfiness.backend.shared.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressCreateRequest(

        @NotBlank(message = "Blank street detected. Please provide a valid street.")
         String street,

         @NotBlank(message = "Blank house number detected. Please provide a valid house number.")
         String houseNumber,

         @NotBlank(message = "Blank postal code detected. Please provide a valid postal code.")
         String postalCode,

         String suffix,

         @NotNull(message = "Address type is required. Please provide a valid address type.")
         AddressType type,

         @NotNull(message = "City information is required. Please provide valid city details.")
         CityCreateRequest city
) {
}