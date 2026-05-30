package com.healfiness.backend.core.application.ports.shoppingcarts;

import com.healfiness.backend.core.domain.entities.shoppingcarts.ShoppingCart;

import java.util.List;

public interface ShoppingCartDbPort {
    List<ShoppingCart> findShoppingCartsByUserId(Long usersId);
}
