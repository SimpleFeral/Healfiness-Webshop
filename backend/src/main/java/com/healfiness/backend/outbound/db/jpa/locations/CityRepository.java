package com.healfiness.backend.outbound.db.jpa.locations;

import com.healfiness.backend.outbound.db.jpaentities.locations.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}