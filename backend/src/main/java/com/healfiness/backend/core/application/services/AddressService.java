package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.domain.dto.locations.AddressCreateRequest;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.shared.components.Schemas;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public AddressResponse createAddressForCurrentUser(
            Long usersId,
            AddressCreateRequest addressCreateRequest
    ) {
        AddressResponse response = new AddressResponse(
                new Schemas.Id(1L),
                addressCreateRequest.street(),
                addressCreateRequest.houseNumber(),
                addressCreateRequest.postalCode(),
                addressCreateRequest.suffix(),
                addressCreateRequest.type(),
                null,
                new Schemas.Id(usersId),
                null
        );
        return response;
    }
}
