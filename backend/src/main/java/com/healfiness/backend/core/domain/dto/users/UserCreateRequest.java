package com.healfiness.backend.core.domain.dto.users;

import com.healfiness.backend.shared.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserCreateRequest(

        @NotBlank(message = "Username cannot be blank")
        @Length(max = 15, message = "Username cannot exceed 15 characters")
        String userName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password,

        String firstName,

        String lastName,

        @Length(max = 50, message = "Phone number cannot exceed 50 characters")
        String phoneNumber,

        @NotNull(message = "Role cannot be null")
        UserRoles role
) {

}