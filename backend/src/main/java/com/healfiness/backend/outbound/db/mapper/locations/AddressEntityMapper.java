package com.healfiness.backend.outbound.db.mapper.locations;

import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressEntityMapper {

    Address toDomainEntity(AddressEntity addressEntity);

    AddressEntity toJpaEntity(Address address);
}
