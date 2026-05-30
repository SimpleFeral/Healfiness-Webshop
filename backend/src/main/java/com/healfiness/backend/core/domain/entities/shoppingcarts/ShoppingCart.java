package com.healfiness.backend.core.domain.entities.shoppingcarts;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.users.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class ShoppingCart extends AbstractObjectMetaData {

    private Long shoppingCartsId;

    private User user;

    private List<CartItem> cartItems;

    private BigDecimal price;

    public ShoppingCart() {
        super();
    }

    public ShoppingCart(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long shoppingCartsId,
            User user,
            List<CartItem> cartItems,
            BigDecimal price
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.shoppingCartsId = shoppingCartsId;
        this.user = user;
        this.cartItems = cartItems;
        this.price = price;
    }

    public Long getShoppingCartsId() {
        return shoppingCartsId;
    }

    public void setShoppingCartsId(Long shoppingCartsId) {
        this.shoppingCartsId = shoppingCartsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
