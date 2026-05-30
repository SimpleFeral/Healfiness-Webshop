package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.UserService;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.util.SortParser;
import com.healfiness.backend.inbound.rest.wrapper.UserPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "Users", description = "Operations related to users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get users", description = "Retrieve a list of all users", operationId = "getUsers",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "A list of users",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserPageResponse.class)
                    )
            )}
    )
    public ResponseEntity<PageResponse<UserResponse>> getUsers(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(name = "sort", required = false) List<String> sort
    ) {
        List<SortOrder> sortOrders = SortParser.parse(sort);

        return ResponseEntity.ok(userService.findAllUsers(page, size, sortOrders));
    }
}
