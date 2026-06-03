package com.healfiness.backend.outbound.db.adapter;

import com.healfiness.backend.core.application.ports.products.ProductDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.outbound.db.jpa.products.ProductRepository;
import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import com.healfiness.backend.outbound.db.mapper.ProductEntityMapper;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductAdapter implements ProductDbPort {

    private final ProductRepository productRepository;

    private final ProductEntityMapper mapper;

    public ProductAdapter(
            ProductRepository productRepository,
            ProductEntityMapper mapper
    ) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Product reserveProduct(Long productId, Integer quantity) {
        ProductEntity retVal = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + productId + " not found"));

       int remaining = retVal.getStockQuantity() - quantity;

        if(remaining < 0) {
            throw new IllegalStateException(
                    "Not enough stock for product with id " + productId);
        }

        retVal.setStockQuantity(remaining);

        return mapper.toDomainEntity(retVal);
    }

    @Override
    public Page<Product> findAllProductsFlat(
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Pageable pageable = PageRequest.of(page, size, SortOrderMapper.mapToSpringSort(sortOrders));
        return productRepository.findAllFlat(pageable);
    }
}
