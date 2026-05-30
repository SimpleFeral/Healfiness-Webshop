package com.healfiness.backend.core.domain.entities.users;

import com.healfiness.backend.core.domain.entities.AbstractObjectMetaData;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.core.domain.entities.orders.Order;
import com.healfiness.backend.core.domain.entities.shoppingcarts.ShoppingCart;
import com.healfiness.backend.shared.UserRoles;

import java.time.Instant;
import java.util.List;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.users.UserEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public class User extends AbstractObjectMetaData {

    private Long usersId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private UserRoles role;

    private List<Address> addresses;

    private List<Order> orders;

    private List<ShoppingCart> shoppingCarts;

    public User() {
        super();
    }

    public User(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long usersId,
            String firstName,
            String lastName,
            String userName,
            String email,
            String password,
            String phoneNumber,
            UserRoles role
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.usersId = usersId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
            Long version,
            Long usersId,
            String firstName,
            String lastName,
            String userName,
            String email,
            String password,
            String phoneNumber,
            UserRoles role,
            List<Address> addresses,
            List<Order> orders,
            List<ShoppingCart> shoppingCarts
    ) {
        super(createUser, createDate, lastModifiedUser, lastModifiedDate, version);
        this.usersId = usersId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.addresses = addresses;
        this.orders = orders;
        this.shoppingCarts = shoppingCarts;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public String toString() {
        return "User{" +
                "usersId=" + usersId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", addresses=" + addresses +
                ", orders=" + orders +
                ", shoppingCarts=" + shoppingCarts +
                "} " + super.toString();
    }
}
