package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.UserService;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/me")
public class MeController {

    private final UserService userService;

    public MeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getMe(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) throws Exception {
        return ResponseEntity.ok(userService.findById(customUserDetails.getUsersId()));
    }
}
