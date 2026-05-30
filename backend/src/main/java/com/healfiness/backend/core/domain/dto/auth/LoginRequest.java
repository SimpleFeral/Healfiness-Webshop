package com.healfiness.backend.core.domain.dto.auth;

public record LoginRequest(
        String login,
        String password
) {
}
