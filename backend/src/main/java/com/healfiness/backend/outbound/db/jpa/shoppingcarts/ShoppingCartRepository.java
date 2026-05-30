package com.healfiness.backend.outbound.db.jpa.shoppingcarts;

import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query("SELECT sc FROM ShoppingCartEntity sc LEFT JOIN FETCH sc.cartItems i " +
            "LEFT JOIN i.product p LEFT JOIN p.category WHERE sc.user.id IN :usersId")
    List<ShoppingCartEntity> findAllByUsersId(Long usersId);
}