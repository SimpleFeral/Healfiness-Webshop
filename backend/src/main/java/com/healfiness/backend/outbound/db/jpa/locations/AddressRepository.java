package com.healfiness.backend.outbound.db.jpa.locations;

import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a LEFT JOIN FETCH a.city " +
            "c LEFT JOIN FETCH c.isoCountryCode WHERE a.user.id IN :usersId")
    Page<Address> findAllByUsersId(Long usersId, PageRequest pageRequest);
}