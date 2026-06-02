package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.AddressService;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.shared.components.Schemas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(path = "/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find address by ID",
            description = "Retrieve details of a specific address by its unique identifier",
            operationId = "findAddressById",
            parameters = @Parameter(name = "addressId", description = "The ID of the address to fetch for",
                    in = ParameterIn.PATH, required = true, schema = @Schema(implementation = Schemas.Id.class)
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Address found and returned successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AddressResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid address ID supplied",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Address with the specified ID not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    public ResponseEntity<AddressResponse> findAddressById(
            @PathVariable Long addressId
    ) {
        AddressResponse address = addressService.findAddressById(addressId);
        return ResponseEntity.ok(address);
    }
}
