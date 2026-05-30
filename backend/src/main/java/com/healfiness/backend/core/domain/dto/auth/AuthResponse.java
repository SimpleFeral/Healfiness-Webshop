package com.healfiness.backend.core.domain.dto.auth;

import com.healfiness.backend.core.domain.dto.users.UserSummary;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        Integer expiresIn,
        UserSummary userSummary
) {
}
