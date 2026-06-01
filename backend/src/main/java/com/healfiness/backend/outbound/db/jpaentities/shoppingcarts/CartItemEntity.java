package com.healfiness.backend.outbound.db.jpaentities.shoppingcarts;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.products.ProductEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long cartItemsId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCartEntity shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer quantity;

    public CartItemEntity() {
        super();
    }

    public CartItemEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long cartItemsId,
            ShoppingCartEntity shoppingCart,
            ProductEntity product,
            Integer quantity
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.cartItemsId = cartItemsId;
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getCartItemsId() {
        return cartItemsId;
    }

    public void setCartItemsId(Long cartItemsId) {
        this.cartItemsId = cartItemsId;
    }

    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItemEntity that = (CartItemEntity) o;
        return Objects.equals(cartItemsId, that.cartItemsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cartItemsId);
    }

    @Override
    public String toString() {
        return "CartItemEntity{" +
                "cartItemsId=" + cartItemsId +
                ", shoppingCart=" + shoppingCart +
                ", product=" + product +
                ", quantity=" + quantity +
                "} " + super.toString();
    }
}
