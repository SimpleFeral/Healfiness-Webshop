package com.healfiness.backend.core.domain.entities.orders;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Order extends AbstractObjectMetaData {

    private Long ordersId;

    private User user;

    private Instant orderDate;

    private OrderStatus orderStatus;

    private List<OrderItem> orderItems;

    private Integer quantity;

    private BigDecimal totalAmount;

    public Order() {
        super();
    }

    public Order(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long ordersId,
            User user,
            Instant orderDate,
            OrderStatus orderStatus,
            List<OrderItem> orderItems,
            Integer quantity,
            BigDecimal totalAmount
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.ordersId = ordersId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(ordersId, that.ordersId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ordersId);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "ordersId=" + ordersId +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                "} " + super.toString();
    }
}
