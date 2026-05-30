package com.healfiness.backend.core.domain.entities.shoppingcarts;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.products.Product;

import java.time.Instant;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.CartItemEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class CartItem extends AbstractObjectMetaData {

    private Long cartItemsId;

    private ShoppingCart shoppingCart;

    private Product product;

    private Integer quantity;

    public CartItem() {
        super();
    }

    public CartItem(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long cartItemsId,
            Product product,
            Integer quantity
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.cartItemsId = cartItemsId;
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long cartItemsId,
            ShoppingCart shoppingCart,
            Product product,
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
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

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemsId=" + cartItemsId +
                ", shoppingCart=" + shoppingCart +
                ", product=" + product +
                ", quantity=" + quantity +
                "} " + super.toString();
    }
}
