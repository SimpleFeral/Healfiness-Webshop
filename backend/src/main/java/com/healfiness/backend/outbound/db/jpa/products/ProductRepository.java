package com.healfiness.backend.outbound.db.jpa.products;

import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}