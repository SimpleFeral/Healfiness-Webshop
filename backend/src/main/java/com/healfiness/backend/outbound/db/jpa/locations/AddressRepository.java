package com.healfiness.backend.outbound.db.jpa.locations;

import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a LEFT JOIN FETCH a.city " +
            "c LEFT JOIN FETCH c.isoCountryCode WHERE a.user.id IN :userIds")
    List<AddressEntity> findAllByUsersId(Long usersId);
}