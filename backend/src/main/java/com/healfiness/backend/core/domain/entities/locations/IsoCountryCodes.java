package com.healfiness.backend.core.domain.entities.locations;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;

import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.locations.IsoCountryCodesEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class IsoCountryCodes extends AbstractObjectMetaData {

    private Long isoCountryCodesId;

    private String isoCountryCode;

    private String countryName;

    public IsoCountryCodes() {
    }

    public IsoCountryCodes(
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
        IsoCountryCodes that = (IsoCountryCodes) o;
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
        return "IsoCountryCodes{" +
                "isoCountryCodeId=" + isoCountryCodesId +
                ", countryCode='" + isoCountryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                "} " + super.toString();
    }
}
