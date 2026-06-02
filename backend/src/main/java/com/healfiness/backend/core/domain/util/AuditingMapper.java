package com.healfiness.backend.core.domain.util;

import com.healfiness.backend.core.domain.dto.MetaData;
import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.shared.components.Schemas;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface AuditingMapper {

    MetaData mapAuditingFields(AbstractObjectMetaData abstractObjectMetaData);

    Schemas.UserName mapToWrapperUserName(String userName);

    Schemas.Timestamp mapToWrapperTimestamp(Instant timestamp);

}
