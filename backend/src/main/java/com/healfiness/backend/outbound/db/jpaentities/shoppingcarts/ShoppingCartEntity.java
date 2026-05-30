package com.healfiness.backend.outbound.db.jpaentities.shoppingcarts;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.users.UserEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long shoppingCartsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItemEntity> cartItems;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public ShoppingCartEntity() {
        super();
    }

    public ShoppingCartEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long shoppingCartsId,
            UserEntity user,
            List<CartItemEntity> cartItems,
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
