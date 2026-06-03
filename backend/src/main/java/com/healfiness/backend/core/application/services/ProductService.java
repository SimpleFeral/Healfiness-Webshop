package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.products.CategoryDbPort;
import com.healfiness.backend.core.application.ports.products.ProductDbPort;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.products.CategorySummary;
import com.healfiness.backend.core.domain.dto.products.ProductResponse;
import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.shared.components.Schemas;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDbPort productDbPort;

    private final CategoryDbPort categoryDbPort;

    public ProductService(
            ProductDbPort productDbPort,
            CategoryDbPort categoryDbPort
    ) {
        this.productDbPort = productDbPort;
        this.categoryDbPort = categoryDbPort;
    }


    public PageResponse<ProductResponse> findAllProducts(
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Page<Product> fetchedProducts = productDbPort.findAllProductsFlat(page, size, sortOrders);

        List<ProductResponse> productResponses = fetchedProducts.stream()
                .map(product -> {
                    product.setCategory(categoryDbPort.findByProductId(product.getProductsId()));
                    return new ProductResponse(
                            product.getProductsId(),
                            product.getName(),
                            new Schemas.Money(product.getPrice()),
                            product.getDescription(),
                            product.getStockQuantity(),
                            product.getCategory() == null
                                    ? null
                                    : new CategorySummary(
                                            product.getCategory().getCategoriesId(),
                                        product.getCategory().getName(),
                                        product.getCategory().getDescription()
                                    ),
                            null
                    );
                })
                .toList();

        return new PageResponse<>(
                productResponses,
                new PageMetaData(
                        fetchedProducts.getNumber(),
                        fetchedProducts.getSize(),
                        fetchedProducts.getTotalElements(),
                        fetchedProducts.getTotalPages(),
                        fetchedProducts.isLast(),
                        SortOrderMapper.mapToDomainSort(fetchedProducts.getSort())
                )
        );
    }
}
