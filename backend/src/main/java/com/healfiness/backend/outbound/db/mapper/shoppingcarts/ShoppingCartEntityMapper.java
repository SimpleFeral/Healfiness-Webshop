package com.healfiness.backend.outbound.db.mapper.shoppingcarts;

import com.healfiness.backend.core.domain.entities.shoppingcarts.ShoppingCart;
import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShoppingCartEntityMapper {
    ShoppingCart toDomainEntity(ShoppingCartEntity shoppingCartEntity);
}
