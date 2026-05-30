package com.healfiness.backend.core.domain.dto.locations;

import com.healfiness.backend.core.domain.dto.locations.city.CityResponse;
import com.healfiness.backend.shared.AddressType;

public class AddressUpdateRequest {

    private String street;

    private String houseNumber;

    private String postalCode;

    private String suffix;

    private AddressType type;

    private CityResponse city;

    public AddressUpdateRequest() {
    }

    public AddressUpdateRequest(
            String street,
            String houseNumber,
            String postalCode,
            String suffix,
            AddressType type,
            CityResponse city
    ) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.suffix = suffix;
        this.type = type;
        this.city = city;
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

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public CityResponse getCity() {
        return city;
    }

    public void setCity(CityResponse city) {
        this.city = city;
    }
}
