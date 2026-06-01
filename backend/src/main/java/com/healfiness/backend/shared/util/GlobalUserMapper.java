package com.healfiness.backend.shared.util;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import com.healfiness.backend.core.domain.dto.shoppingcarts.ShoppingCartResponse;
import com.healfiness.backend.core.domain.dto.users.UserFlatDatabaseProjection;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import com.healfiness.backend.outbound.db.jpaentities.users.UserEntity;
import com.healfiness.backend.shared.components.Schemas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GlobalUserMapper {

    @Mapping(source = "createUser", target = "metaData.createUser")
    @Mapping(source = "createDate", target = "metaData.createDate")
    @Mapping(source = "lastModifiedUser", target = "metaData.lastModifiedUser")
    @Mapping(source = "lastModifiedDate", target = "metaData.lastModifiedDate")
    @Mapping(source = "version", target = "metaData.version")
    UserResponse toUserResponse(User user);

    UserResponse toDomain(
            UserFlatDatabaseProjection flatUser,
            List<AddressResponse> addressResponses,
            List<OrderResponse> orderResponses,
            List<ShoppingCartResponse> shoppingCartResponses
    );

    List<AddressResponse> toAddressDomain(List<AddressEntity> addressEntities);

    List<OrderResponse> toOrderDomain(List<OrderEntity> orderEntities);

    List<ShoppingCartResponse> toShoppingCartDomain(List<ShoppingCartEntity> shoppingCartEntities);

    Schemas.Id maptoWrapperId(Long value);

    Schemas.Money maptoWrapperMoney(BigDecimal value);

    Schemas.Timestamp maptoWrapperTimestamp(Instant value);

    Schemas.UserName maptoWrapperUserName(String value);

    User toDomainEntity(UserEntity userEntity);
}
