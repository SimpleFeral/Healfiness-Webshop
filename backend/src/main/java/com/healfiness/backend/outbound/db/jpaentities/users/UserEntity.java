package com.healfiness.backend.outbound.db.jpaentities.users;

import com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import com.healfiness.backend.shared.UserRoles;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractObjectMetaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long usersId;

    private String firstName;

    private String lastName;

    @Column(length = 15, nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 50)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "user_roles", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private UserRoles role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShoppingCartEntity> shoppingCarts;

    public UserEntity() {
        super();
    }

    public UserEntity(
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
            List<AddressEntity> addresses,
            List<OrderEntity> orders,
            List<ShoppingCartEntity> shoppingCarts
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

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public List<ShoppingCartEntity> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCartEntity> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @PrePersist
    public void prePersist() {
        if (getCreateUser()  == null || StringUtils.isBlank(getCreateUser())
                || getCreateUser().equalsIgnoreCase("anonymousUser")) {
            setCreateUser(getUserName());
        }

        if (getLastModifiedUser() == null || StringUtils.isBlank(getLastModifiedUser())
                || getLastModifiedUser().equalsIgnoreCase("anonymousUser")) {
            setLastModifiedUser(getUserName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(usersId, that.usersId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usersId);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
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
