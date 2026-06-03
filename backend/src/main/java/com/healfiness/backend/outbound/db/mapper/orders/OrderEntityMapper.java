package com.healfiness.backend.outbound.db.mapper.orders;

import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderEntityMapper {
    Order toDomainEntity(OrderEntity orderEntity);

    OrderEntity toJpaEntity(Order orderToCreate);
}
