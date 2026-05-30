package com.healfiness.backend.outbound.db.adapter.locations;

import com.healfiness.backend.core.application.ports.locations.AddressDbPort;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.outbound.db.jpa.locations.AddressRepository;
import com.healfiness.backend.outbound.db.mapper.locations.AddressEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressAdapter implements AddressDbPort {

    private final AddressRepository addressRepository;

    private final AddressEntityMapper mapper;

    public AddressAdapter(
            AddressRepository addressRepository,
            AddressEntityMapper mapper
    ) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Address> findAddressesByUserId(Long usersId) {
        return addressRepository.findAllByUsersId(usersId)
                .stream()
                .map(mapper::toDomainEntity)
                .toList();
    }
}
