package com.healfiness.backend.outbound.db.jpaentities.shipments;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import com.healfiness.backend.shared.ShipmentStatus;
import com.healfiness.backend.shared.ShippingType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


@Entity
@Table(name = "shipments")
public class ShipmentEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false)
    private ShippingType shippingType;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;

    @Column(length = 12, nullable = false)
    private String trackingNumber;

    private Instant shippedAt;

    public ShipmentEntity() {
        super();
    }

    public ShipmentEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long shipmentId,
            OrderEntity order,
            AddressEntity address,
            ShippingType shippingType,
            ShipmentStatus shipmentStatus,
            String trackingNumber,
            Instant shippedAt
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.shipmentId = shipmentId;
        this.order = order;
        this.address = address;
        this.shippingType = shippingType;
        this.shipmentStatus = shipmentStatus;
        this.trackingNumber = trackingNumber;
        this.shippedAt = shippedAt;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Instant getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(Instant shippedAt) {
        this.shippedAt = shippedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShipmentEntity that = (ShipmentEntity) o;
        return Objects.equals(shipmentId, that.shipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(shipmentId);
    }

    @Override
    public String toString() {
        return "ShipmentEntity{" +
                "shipmentId=" + shipmentId +
                ", order=" + order +
                ", address=" + address +
                ", shippingType=" + shippingType +
                ", shipmentStatus=" + shipmentStatus +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", shippedAt=" + shippedAt +
                "} " + super.toString();
    }
}
