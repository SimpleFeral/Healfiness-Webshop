package com.healfiness.backend.outbound.db.jpa.shoppingcarts;

import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}