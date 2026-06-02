package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.domain.dto.orders.OrderCreateRequest;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderDbPort orderDbPort;

    public OrderService(OrderDbPort orderDbPort) {
        this.orderDbPort = orderDbPort;
    }

    public OrderResponse createOrderForCurrentUser(
            Long usersId,
            OrderCreateRequest orderCreateRequest
    ) {
        return null;
    }
}
