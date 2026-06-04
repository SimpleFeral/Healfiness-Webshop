package com.healfiness.backend.outbound.db.adapter;

import com.healfiness.backend.core.application.ports.products.CategoryDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.entities.products.Category;
import com.healfiness.backend.outbound.db.jpa.products.CategoryRepository;
import com.healfiness.backend.outbound.db.mapper.CategoryEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryAdapter implements CategoryDbPort {

    private final CategoryRepository categoryRepository;

    private final CategoryEntityMapper mapper;

    public CategoryAdapter(
            CategoryRepository categoryRepository,
            CategoryEntityMapper mapper
    ) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    @Override
    public Category findByProductId(Long productsId) {
        return categoryRepository.findByProducts_ProductsId(productsId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found for product id: " + productsId));
    }
}
