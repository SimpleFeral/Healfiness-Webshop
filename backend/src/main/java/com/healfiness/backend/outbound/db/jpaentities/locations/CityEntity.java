package com.healfiness.backend.outbound.db.jpaentities.locations;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "cities")
public class CityEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long citiesId;

    @Column(length = 3, nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iso_country_codes_id")
    private IsoCountryCodesEntity isoCountryCode;

    public CityEntity() {
        super();
    }

    public CityEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long citiesId,
            String shortName,
            String name,
            IsoCountryCodesEntity isoCountryCode
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

    public IsoCountryCodesEntity getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(IsoCountryCodesEntity isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
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
        return "CityEntity{" +
                "citiesId=" + citiesId +
                ", shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                ", isoCountryCode=" + isoCountryCode +
                "} " + super.toString();
    }
}
