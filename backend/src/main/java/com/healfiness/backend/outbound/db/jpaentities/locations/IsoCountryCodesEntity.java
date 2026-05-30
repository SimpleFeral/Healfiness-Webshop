package com.healfiness.backend.outbound.db.jpaentities.locations;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "iso_country_codes")
public class IsoCountryCodesEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "iso_country_codes_id", columnDefinition = "SERIAL")
    private Long isoCountryCodesId;

    @Column(length = 3,  nullable = false)
    private String isoCountryCode;

    @Column(nullable = false)
    private String countryName;

    public IsoCountryCodesEntity() {
    }

    public IsoCountryCodesEntity(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long isoCountryCodesId,
            String isoCountryCode,
            String countryName
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.isoCountryCodesId = isoCountryCodesId;
        this.isoCountryCode = isoCountryCode;
        this.countryName = countryName;
    }

    public Long getIsoCountryCodesId() {
        return isoCountryCodesId;
    }

    public void setIsoCountryCodesId(Long isoCountryCodesId) {
        this.isoCountryCodesId = isoCountryCodesId;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IsoCountryCodesEntity that = (IsoCountryCodesEntity) o;
        return Objects.equals(isoCountryCodesId, that.isoCountryCodesId)
                && Objects.equals(isoCountryCode, that.isoCountryCode)
                && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoCountryCodesId, isoCountryCode, countryName);
    }

    @Override
    public String toString() {
        return "IsoCountryCodesEntity{" +
                "isoCountryCodeId=" + isoCountryCodesId +
                ", countryCode='" + isoCountryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                "} " + super.toString();
    }
}
