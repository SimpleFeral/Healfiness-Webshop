package com.healfiness.backend.outbound.db.jpaentities.orders;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItemEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long orderItemsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Instant orderDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal singlePriceAtOrderTime;

    public OrderItemEntity() {
        super();
    }

    public OrderItemEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long orderItemsId,
            OrderEntity order,
            ProductEntity product,
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
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
        OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(orderItemsId, that.orderItemsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderItemsId);
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "orderItemsId=" + orderItemsId +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", singlePriceAtOrderTime=" + singlePriceAtOrderTime +
                "} " + super.toString();
    }
}
