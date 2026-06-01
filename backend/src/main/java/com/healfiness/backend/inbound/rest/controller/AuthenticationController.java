package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.AuthService;
import com.healfiness.backend.core.domain.dto.auth.AuthResponse;
import com.healfiness.backend.core.domain.dto.auth.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operations related to authentication and authorization")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Request login",
            description = "Authenticate a user and return an authentication token",
            operationId = "requestLogin",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful, returns an authentication token",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication failed, invalid credentials",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            })
    public ResponseEntity<AuthResponse> requestLogin(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.requestLogin(loginRequest);

        if (authResponse == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(authResponse);
    }

}
