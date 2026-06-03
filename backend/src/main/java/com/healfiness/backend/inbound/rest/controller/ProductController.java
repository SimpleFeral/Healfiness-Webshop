package com.healfiness.backend.inbound.rest.controller;

import com.healfiness.backend.core.application.services.ProductService;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.products.ProductPageResponse;
import com.healfiness.backend.core.domain.dto.products.ProductResponse;
import com.healfiness.backend.core.domain.util.SortParser;
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
@RequestMapping("/products")
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get products",
            description = "Retrieve a list of all products",
            operationId = "getProducts",
            responses = {
            @ApiResponse(responseCode = "200", description = "A list of products",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductPageResponse.class)
                    )
            )
    }
    )
    public ResponseEntity<PageResponse<ProductResponse>> getProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(name = "sort", required = false) List<String> sort
    ) {
        List<SortOrder> sortOrders = SortParser.parse(sort);

        return ResponseEntity.ok(productService.findAllProducts(page, size, sortOrders));
    }
}
