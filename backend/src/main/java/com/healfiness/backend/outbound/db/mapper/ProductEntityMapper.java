package com.healfiness.backend.outbound.db.mapper;

import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toDomainEntity(ProductEntity productEntity);
}
