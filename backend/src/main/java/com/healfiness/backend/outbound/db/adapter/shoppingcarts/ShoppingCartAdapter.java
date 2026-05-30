package com.healfiness.backend.outbound.db.adapter.shoppingcarts;

import com.healfiness.backend.core.application.ports.shoppingcarts.ShoppingCartDbPort;
import com.healfiness.backend.core.domain.entities.shoppingcarts.ShoppingCart;
import com.healfiness.backend.outbound.db.jpa.shoppingcarts.ShoppingCartRepository;
import com.healfiness.backend.outbound.db.mapper.shoppingcarts.ShoppingCartEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartAdapter implements ShoppingCartDbPort {

    private final ShoppingCartRepository shoppingCartRepository;

    private final ShoppingCartEntityMapper shoppingCartEntityMapper;

    public ShoppingCartAdapter(
            ShoppingCartRepository shoppingCartRepository,
            ShoppingCartEntityMapper shoppingCartEntityMapper
    ) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartEntityMapper = shoppingCartEntityMapper;
    }

    @Override
    public List<ShoppingCart> findShoppingCartsByUserId(Long usersId) {
        return shoppingCartRepository.findAllByUsersId(usersId)
                .stream()
                .map(shoppingCartEntityMapper::toDomainEntity)
                .toList();
    }
}
