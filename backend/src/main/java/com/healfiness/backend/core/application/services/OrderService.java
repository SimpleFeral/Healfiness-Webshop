package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.application.ports.products.ProductDbPort;
import com.healfiness.backend.core.domain.dto.orders.OrderCreateRequest;
import com.healfiness.backend.core.domain.dto.orders.OrderItemResponse;
import com.healfiness.backend.core.domain.dto.orders.OrderResponse;
import com.healfiness.backend.core.domain.dto.products.ProductSummary;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.core.domain.entities.orders.OrderItem;
import com.healfiness.backend.core.domain.entities.products.Product;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.components.Schemas;
import org.springframework.stereotype.Service;

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

    private OrderResponse mapToOrderResponse(Order savedOrder) {
        if (savedOrder == null) {
            return null;
        }
        return new OrderResponse(
                new Schemas.Id(savedOrder.getOrdersId()),
                savedOrder.getOrderStatus(),
                new Schemas.Money(savedOrder.getTotalAmount()),
                new Schemas.Timestamp(savedOrder.getOrderDate()),
                savedOrder.getQuantity(),
                savedOrder.getOrderItems().stream().map(
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
                new Schemas.Id(savedOrder.getUser().getUsersId())
        );
    }
}
