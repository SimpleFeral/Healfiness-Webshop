package com.healfiness.backend.core.domain.entities.locations;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.locations.CityEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class City extends AbstractObjectMetaData {

    private Long citiesId;

    private String shortName;

    private String name;

    private IsoCountryCodes isoCountryCode;

    public City() {
        super();
    }

    public City(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long citiesId,
            String shortName,
            String name,
            IsoCountryCodes isoCountryCode
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.citiesId = citiesId;
        this.shortName = shortName;
        this.name = name;
        this.isoCountryCode = isoCountryCode;
    }

    public Long getCitiesId() {
        return citiesId;
    }

    public void setCitiesId(Long citiesId) {
        this.citiesId = citiesId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IsoCountryCodes getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(IsoCountryCodes isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City that = (City) o;
        return Objects.equals(citiesId, that.citiesId)
                && Objects.equals(shortName, that.shortName)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citiesId, shortName, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "citiesId=" + citiesId +
                ", shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", isoCountryCode=" + isoCountryCode +
                "} " + super.toString();
    }
}
