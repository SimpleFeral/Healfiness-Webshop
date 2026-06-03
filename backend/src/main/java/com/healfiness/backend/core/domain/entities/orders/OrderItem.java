package com.healfiness.backend.core.domain.entities.orders;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.products.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.orders.OrderItemEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class OrderItem extends AbstractObjectMetaData {

    private Long orderItemsId;

    private Order order;

    private Product product;

    private Integer quantity;

    private Instant orderDate;

    private BigDecimal singlePriceAtOrderTime;

    public OrderItem() {
        super();
    }

    public OrderItem(
            Order order,
            Product product,
            Integer quantity,
            Instant orderDate,
            BigDecimal singlePriceAtOrderTime
    ) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.singlePriceAtOrderTime = singlePriceAtOrderTime;
    }

    public OrderItem(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long orderItemsId,
            Order order,
            Product product,
            Integer quantity,
            Instant orderDate,
            BigDecimal singlePriceAtOrderTime
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.orderItemsId = orderItemsId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.singlePriceAtOrderTime = singlePriceAtOrderTime;
    }

    public Long getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(Long orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getSinglePriceAtOrderTime() {
        return singlePriceAtOrderTime;
    }

    public void setSinglePriceAtOrderTime(BigDecimal singlePriceAtOrderTime) {
        this.singlePriceAtOrderTime = singlePriceAtOrderTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return Objects.equals(orderItemsId, that.orderItemsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderItemsId);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemsId=" + orderItemsId +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", singlePriceAtOrderTime=" + singlePriceAtOrderTime +
                "} " + super.toString();
    }
}
