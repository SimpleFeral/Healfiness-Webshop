package com.healfiness.backend.core.application.ports.products;

import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.products.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDbPort {
    Product reserveProduct(Long productId, Integer quantity);

    Page<Product> findAllProductsFlat(
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    );
}
