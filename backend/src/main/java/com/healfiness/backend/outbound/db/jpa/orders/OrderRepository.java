package com.healfiness.backend.outbound.db.jpa.orders;

import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderItems i " +
            "LEFT JOIN i.product p LEFT JOIN p.category WHERE o.user.id IN :usersId")
    Page<Order> findAllByUsersId(Long usersId, Pageable pageable);
}