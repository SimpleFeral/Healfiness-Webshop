package com.healfiness.backend.outbound.db.jpa.orders;

import com.healfiness.backend.outbound.db.jpaentities.orders.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}