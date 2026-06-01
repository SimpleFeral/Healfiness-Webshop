package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.AddressService;
import com.healfiness.backend.core.application.services.UserService;
import com.healfiness.backend.core.domain.dto.locations.AddressCreateRequest;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/me")
@Tag(name = "Me (current user)", description = "Operations related to the current logged in user")
public class MeController {

    private final UserService userService;

    private final AddressService addressService;

    public MeController(
            UserService userService,
            AddressService addressService
    ) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getMe(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) throws Exception {
        return ResponseEntity.ok(userService.findById(customUserDetails.getUsersId()));
    }

    @PostMapping(path = "/addresses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Add an address for current user",
            description = "Create a new address associated with the current logged in user",
            operationId = "createUsersMeAddress",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Address created successfully for the current user",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AddressResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid request body or missing required fields",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Unauthorized - user is not authenticated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    public ResponseEntity<AddressResponse> createAddressForCurrentUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody AddressCreateRequest addressCreateRequest
    ) {
        AddressResponse response = addressService
                .createAddressForCurrentUser(
                        customUserDetails.getUsersId(), addressCreateRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/addresses/{addressId}")
                .buildAndExpand(response.addressesId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
