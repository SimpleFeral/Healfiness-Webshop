package com.healfiness.backend.outbound.db.jpa.orders;

import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderItems i " +
            "LEFT JOIN i.product p LEFT JOIN p.category WHERE o.user.id IN :usersId")
    List<OrderEntity> findAllByUsersId(Long usersId);
}