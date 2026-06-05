package com.healfiness.backend.outbound.db.adapter.orders;

import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.outbound.db.jpa.orders.OrderRepository;
import com.healfiness.backend.outbound.db.mapper.orders.OrderEntityMapper;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Order> findOrdersByUserId(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Pageable pageable = PageRequest.of(page, size, SortOrderMapper.mapToSpringSort(sortOrders));
        return orderRepository.findAllByUsersId(usersId, pageable);
    }

    @Override
    public Order save(Order orderToCreate) {
        return mapper.toDomainEntity(
                orderRepository.save(
                        mapper.toJpaEntity(orderToCreate)
                )
        );
    }

    @Override
    public Order findOrderById(Long ordersId) {
        return orderRepository.findById(ordersId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order with ID " + ordersId + " not found"));
    }
}
