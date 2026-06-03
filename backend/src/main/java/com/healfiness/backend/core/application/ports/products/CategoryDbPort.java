package com.healfiness.backend.core.application.ports.products;

import com.healfiness.backend.core.domain.entities.products.Category;

public interface CategoryDbPort {
    Category findByProductId(Long productsId);
}
