package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.application.ports.products.ProductDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.UpdateFailureException;
import com.healfiness.backend.core.domain.dto.orders.OrderCreateRequest;
import com.healfiness.backend.core.domain.dto.orders.OrderItemResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderUpdateRequest;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.products.ProductSummary;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.core.domain.entities.orders.OrderItem;
import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.components.Schemas;
import com.healfiness.backend.shared.util.SortOrderMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    private final OrderDbPort orderDbPort;

    private final ProductDbPort productDbPort;

    public OrderService(
            OrderDbPort orderDbPort,
            ProductDbPort productDbPort
    ) {
        this.orderDbPort = orderDbPort;
        this.productDbPort = productDbPort;
    }

    public OrderResponse createOrderForCurrentUser(
            Long usersId,
            OrderCreateRequest orderCreateRequest
    ) {
        User currentUser = new User();
        currentUser.setUsersId(usersId);

        Order orderToCreate = new Order();
        orderToCreate.setUser(currentUser);
        orderToCreate.setOrderDate(Instant.now());
        orderToCreate.setOrderStatus(orderCreateRequest.status());
        orderToCreate.setQuantity(orderCreateRequest.quantity());
        orderToCreate.setTotalAmount(orderCreateRequest.totalAmount().value());

        List<OrderItem> orderItems = orderCreateRequest.orderItems()
                .stream()
                .map(item -> {
                    Product orderedProduct =
                            productDbPort.reserveProduct(item.productId(), item.quantity());

                    return new OrderItem(orderToCreate, orderedProduct, item.quantity(),
                                         orderToCreate.getOrderDate(), orderedProduct.getPrice()
                    );
                }).toList();

        orderToCreate.setOrderItems(orderItems);

        return mapToOrderResponse(orderDbPort.save(orderToCreate));
    }

    private OrderResponse mapToOrderResponse(Order orderToMap) {
        if (orderToMap == null) {
            return null;
        }
        return new OrderResponse(
                new Schemas.Id(orderToMap.getOrdersId()),
                orderToMap.getOrderStatus(),
                new Schemas.Money(orderToMap.getTotalAmount()),
                new Schemas.Timestamp(orderToMap.getOrderDate()),
                orderToMap.getQuantity(),
                orderToMap.getOrderItems().stream().map(
                        item -> new OrderItemResponse(
                                new Schemas.Id(item.getOrderItemsId()),
                                item.getQuantity(),
                                new Schemas.Money(item.getSinglePriceAtOrderTime()),
                                new ProductSummary(
                                        new Schemas.Id(item.getProduct().getProductsId()),
                                        item.getProduct().getName(),
                                        new Schemas.Money(item.getProduct().getPrice())
                                )
                        )
                ).toList(),
                new Schemas.Id(orderToMap.getUser().getUsersId())
        );
    }

    public PageResponse<OrderResponse> findOrdersByCurrentUser(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Page<Order> fetchedOrders = orderDbPort.findOrdersByUserId(usersId, page, size, sortOrders);
        return new PageResponse<>(
                fetchedOrders.getContent().stream()
                        .map(this::mapToOrderResponse)
                        .toList(),
                new PageMetaData(
                        fetchedOrders.getNumber(),
                        fetchedOrders.getSize(),
                        fetchedOrders.getTotalElements(),
                        fetchedOrders.getTotalPages(),
                        fetchedOrders.isLast(),
                        SortOrderMapper.mapToDomainSort(fetchedOrders.getSort())
                )
        );
    }

    public OrderResponse findOrderById(Long ordersId) {
        Order foundOrder = orderDbPort.findOrderById(ordersId);
        return mapToOrderResponse(foundOrder);
    }

    public OrderResponse updateOrderById(
            @Min(value = 1, message = "ID must be a positive integer") Long value,
            @Valid OrderUpdateRequest orderUpdateRequest
    ) {
        Order existingOrder = orderDbPort.findOrderById(value);

        for (Field field : OrderUpdateRequest.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object newValue = field.get(orderUpdateRequest);
                if (newValue != null) {
                    field.set(existingOrder, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new UpdateFailureException("Failed to update order", e);
            }
        }

        Order updatedOrder = orderDbPort.save(existingOrder);
        return mapToOrderResponse(updatedOrder);
    }
}
