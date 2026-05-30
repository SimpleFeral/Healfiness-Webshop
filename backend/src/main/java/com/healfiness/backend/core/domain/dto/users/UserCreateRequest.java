package com.healfiness.backend.core.domain.dto.users;

import com.healfiness.backend.shared.UserRoles;

public record UserCreateRequest(
    String userName,
    String email,
    String password,
    String firstName,
    String lastName,
    String phoneNumber,
    UserRoles role
) {

}