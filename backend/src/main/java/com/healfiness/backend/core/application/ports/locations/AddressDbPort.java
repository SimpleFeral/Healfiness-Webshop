package com.healfiness.backend.core.application.ports.locations;

import com.healfiness.backend.core.domain.entities.locations.Address;

import java.util.List;

public interface AddressDbPort {
    List<Address> findAddressesByUserId(Long usersId);
}
