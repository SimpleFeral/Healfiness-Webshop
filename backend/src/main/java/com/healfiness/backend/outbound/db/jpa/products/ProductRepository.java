package com.healfiness.backend.outbound.db.jpa.products;

import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT new com.healfiness.backend.core.domain.entities.products.Product(" +
            "p.createUser, p.createDate, p.lastModifiedUser, p.lastModifiedDate, p.version, " +
            "p.productsId, p.name, p.description, p.price, p.stockQuantity) " +
            "FROM ProductEntity p")
    Page<Product> findAllFlat(Pageable pageable);
}