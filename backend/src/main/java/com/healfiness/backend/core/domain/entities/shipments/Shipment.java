package com.healfiness.backend.core.domain.entities.shipments;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.shared.ShipmentStatus;
import com.healfiness.backend.shared.ShippingType;

import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.shipments.ShipmentEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Shipment extends AbstractObjectMetaData {

    private Long shipmentId;

    private Order order;

    private Address address;

    private ShippingType shippingType;

    private ShipmentStatus shipmentStatus;

    private String trackingNumber;

    private Instant shippedAt;

    public Shipment() {
        super();
    }

    public Shipment(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long shipmentId,
            Order order,
            Address address,
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
        Shipment that = (Shipment) o;
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
