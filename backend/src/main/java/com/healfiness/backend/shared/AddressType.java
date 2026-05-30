package com.healfiness.backend.shared;

public enum AddressType {

    HOME("Home Address"),
    PICK_UP("Pick Up Address"),
    DELIVERY("Delivery Address"),
    INVOICE("Invoice Address");

    private final String value;

    AddressType(String value) {
        this.value = value;
    }

    public AddressType fromValue(String value) {
        for (AddressType addressType : AddressType.values()) {
            if (addressType.value.equals(value)) {
                return addressType;
            }
        }
        return HOME;
    }

}
