package com.healfiness.backend.outbound.db.jpa.locations;

import com.healfiness.backend.outbound.db.jpaentities.locations.IsoCountryCodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsoCountryCodesRepository extends JpaRepository<IsoCountryCodesEntity, Long> {
}