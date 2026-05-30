package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.domain.dto.auth.AuthResponse;
import com.healfiness.backend.core.domain.dto.auth.LoginRequest;
import com.healfiness.backend.core.domain.dto.users.UserSummary;
import com.healfiness.backend.security.CustomUserDetails;
import com.healfiness.backend.security.CustomUserDetailsService;
import com.healfiness.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthResponse requestLogin(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.login(), loginRequest.password()));

        CustomUserDetails customUserDetails
                = (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginRequest.login());

        String token = jwtService.generateAccessToken(customUserDetails);
        String refreshToken = jwtService.generateRefreshToken(customUserDetails);

        UserSummary userSummary = new UserSummary(
                customUserDetails.getUsersId(),
                customUserDetails.getUsername(),
                customUserDetails.getAuthorities().stream().findFirst().orElseThrow().getAuthority()
        );
        return new AuthResponse(token, refreshToken, 3600, userSummary);
    }
}
