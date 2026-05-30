package com.healfiness.backend.outbound.db.adapter.orders;

import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.outbound.db.jpa.orders.OrderRepository;
import com.healfiness.backend.outbound.db.mapper.orders.OrderEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderAdapter implements OrderDbPort {

    private final OrderRepository orderRepository;

    private final OrderEntityMapper mapper;

    public OrderAdapter(
            OrderRepository orderRepository,
            OrderEntityMapper mapper
    ) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Order> findOrdersByUserId(Long usersId) {
        return orderRepository.findAllByUsersId(usersId)
                .stream()
                .map(mapper::toDomainEntity)
                .toList();
    }
}
