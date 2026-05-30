package com.healfiness.backend.outbound.db.jpa.users;

import com.healfiness.backend.core.domain.dto.users.UserFlatDatabaseProjection;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import com.healfiness.backend.outbound.db.jpaentities.users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT new com.healfiness.backend.core.domain.dto.users.UserFlatDatabaseProjection(" +
            "u.usersId, u.userName, u.email, u.firstName, u.lastName, u.phoneNumber, u.role, " +
            "u.createUser, u.createDate, u.lastModifiedUser, u.lastModifiedDate, u.version" +
            ") FROM UserEntity u")
    Page<UserFlatDatabaseProjection> findAllFlat(Pageable pageable);

    @Query("SELECT a FROM AddressEntity a LEFT JOIN FETCH a.city " +
            "c LEFT JOIN FETCH c.isoCountryCode WHERE a.user.id IN :userIds")
    List<AddressEntity> findAddressesByUserIds(@Param("userIds") List<Long> userIds);

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderItems i " +
            "LEFT JOIN i.product p LEFT JOIN p.category WHERE o.user.id IN :usersId")
    List<OrderEntity> findOrdersByUserIds(@Param("userIds") List<Long> userIds);

    @Query("SELECT sc FROM ShoppingCartEntity sc LEFT JOIN FETCH sc.cartItems i " +
            "LEFT JOIN i.product p LEFT JOIN p.category WHERE sc.user.id IN :usersId")
    List<ShoppingCartEntity> findShoppingCartsByUserIds(@Param("userIds") List<Long> userIds);

    @Query("SELECT new com.healfiness.backend.core.domain.entities.users.User(" +
            "u.createUser, u.createDate, u.lastModifiedUser, u.lastModifiedDate, u.version, " +
            "u.usersId, u.firstName, u.lastName, u.userName, u.email, u.password, u.phoneNumber, " +
            "u.role) FROM UserEntity u")
    Page<User> findAllUsersFlat(Pageable pageable);

    @Query("select u from UserEntity u where upper(u.userName) = upper(?1) or upper(u.email) = upper(?2)")
    Optional<UserEntity> findByUserNameOrEmail(
            String userName,
            String email
    );
}