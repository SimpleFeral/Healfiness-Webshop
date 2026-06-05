package com.healfiness.backend.core.application.ports.orders;

import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.orders.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDbPort {
    Page<Order> findOrdersByUserId(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    );

    Order save(Order orderToCreate);

    Order findOrderById(Long ordersId);
}
