package com.healfiness.backend.core.domain.dto.users;

import com.healfiness.backend.shared.UserRoles;

import java.time.Instant;

public record UserFlatDatabaseProjection(
        Long usersId,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        UserRoles role,
        String createUser,
        Instant createDate,
        String lastModifiedUser,
        Instant lastModifiedDate,
        Long version
) {
}
