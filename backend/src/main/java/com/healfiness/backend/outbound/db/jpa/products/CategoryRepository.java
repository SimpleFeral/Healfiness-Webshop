package com.healfiness.backend.outbound.db.jpa.products;

import com.healfiness.backend.outbound.db.jpaentities.products.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}