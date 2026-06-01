package com.healfiness.backend.outbound.db.jpaentities.locations;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.users.UserEntity;
import com.healfiness.backend.shared.AddressType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class AddressEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long addressesId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String postalCode;

    private String suffix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "address_type", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public AddressEntity() {
        super();
    }

    public AddressEntity(
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
            CityEntity city,
            AddressType addressType,
            UserEntity user
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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
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
