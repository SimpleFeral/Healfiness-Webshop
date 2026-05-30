package com.healfiness.backend.core.domain.entities.payments;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.shared.PaymentMethode;
import com.healfiness.backend.shared.PaymentStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.payments.PaymentEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Payment extends AbstractObjectMetaData {

    private Long paymentId;

    private Order order;

    private PaymentMethode paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionId;

    private Instant paidAt;

    public Payment() {
        super();
    }

    public Payment(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long paymentId,
            Order order,
            PaymentMethode paymentMethod,
            PaymentStatus paymentStatus,
            String transactionId,
            Instant paidAt
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.paymentId = paymentId;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.paidAt = paidAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethode getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethode paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment that = (Payment) o;
        return Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(paymentId);
    }

    @Override
public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", order=" + order +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                ", transactionId='" + transactionId +
                ", paidAt=" + paidAt +
                "} " + super.toString();
    }
}
