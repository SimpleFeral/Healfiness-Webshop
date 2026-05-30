package com.healfiness.backend.core.application.ports.orders;

import com.healfiness.backend.core.domain.entities.orders.Order;

import java.util.List;

public interface OrderDbPort {
    List<Order> findOrdersByUserId(Long usersId);
}
