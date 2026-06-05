package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.OrderService;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders and their details")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{ordersId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find order by ID",
            description = "Retrieve details of a specific order by its unique identifier",
            operationId = "findOrderById",
            parameters = @Parameter(name = "ordersId", description = "The ID of the order to fetch for",
                    in = ParameterIn.PATH, required = true, schema = @Schema(implementation = Long.class)
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order found and returned successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid order ID supplied",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Order with the specified ID not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    public ResponseEntity<OrderResponse> findOrderById(
            @Valid @PathVariable @NotNull(message = "OrdersId cannot be null")
            @Min(value = 1, message = "ID must be a positive integer")
            @Schema(implementation = Long.class, example = "1") Long ordersId
    ) {
        return ResponseEntity.ok(orderService.findOrderById(ordersId));
    }

    @PatchMapping(path = "/{ordersId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an order partially by identifier",
            description = "Partially update an existing order's details using its unique identifier",
            operationId = "updateOrderById",
            parameters = @Parameter(name = "ordersId", description = "The ID of the order to update",
                    in = ParameterIn.PATH, required = true, schema = @Schema(implementation = Long.class)
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated order fields",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderUpdateRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid input data or order ID supplied",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Order with the specified ID not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    )
            }
    )
    public ResponseEntity<OrderResponse> updateOrderById(
            @Valid @PathVariable @NotNull(message = "OrdersId cannot be null")
            @Min(value = 1, message = "ID must be a positive integer")
            @Schema(implementation = Long.class, example = "1") Long ordersId,
            @Valid @RequestBody OrderUpdateRequest orderUpdateRequest
    ) {
        return ResponseEntity.ok(orderService.updateOrderById(ordersId, orderUpdateRequest));
    }
}
