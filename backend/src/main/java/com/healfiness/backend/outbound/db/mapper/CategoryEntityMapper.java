package com.healfiness.backend.outbound.db.mapper;

import com.healfiness.backend.core.domain.entities.products.Category;
import com.healfiness.backend.outbound.db.jpaentities.products.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryEntityMapper {
    Category toDomainEntity(CategoryEntity categoryEntity);
}
