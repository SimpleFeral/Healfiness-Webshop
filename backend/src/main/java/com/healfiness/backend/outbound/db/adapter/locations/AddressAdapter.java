package com.healfiness.backend.outbound.db.adapter.locations;

import com.healfiness.backend.core.application.ports.locations.AddressDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.outbound.db.jpa.locations.AddressRepository;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.mapper.locations.AddressEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Address findById(Long addressId) {
        return addressRepository.findById(addressId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Address with id " + addressId + " not found"));
    }

    @Override
    @Transactional
    public Address save(Address addressToCreate) {
        AddressEntity savedEntity = addressRepository.save(mapper.toJpaEntity(addressToCreate));
        return mapper.toDomainEntity(savedEntity);
    }

    @Override
    public Page<Address> findAddressesByCurrentUser(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        return null;
    }
}
