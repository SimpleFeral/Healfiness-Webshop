package com.healfiness.backend.core.domain.entities.locations;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.AddressType;

import java.time.Instant;
import java.util.Objects;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class Address extends AbstractObjectMetaData {

    private Long addressesId;

    private String street;

    private String houseNumber;

    private String postalCode;

    private String suffix;

    private City city;

    private AddressType addressType;

    private User user;

    public Address() {
        super();
    }

    public Address(
            String street,
            String houseNumber,
            String postalCode,
            String suffix,
            City city,
            AddressType addressType,
            User user
    ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.suffix = suffix;
        this.city = city;
        this.addressType = addressType;
        this.user = user;
    }

    public Address(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long addressesId,
            String street,
            String houseNumber,
            String postalCode,
            String suffix,
            City city,
            AddressType addressType,
            User user
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.addressesId = addressesId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.suffix = suffix;
        this.city = city;
        this.addressType = addressType;
        this.user = user;
    }

    public Long getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(Long addressesId) {
        this.addressesId = addressesId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return Objects.equals(addressesId, that.addressesId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addressesId);
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "addressesId=" + addressesId +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", suffix='" + suffix + '\'' +
                ", city=" + city +
                ", addressType=" + addressType +
                ", user=" + user +
                "} " + super.toString();
    }
}
