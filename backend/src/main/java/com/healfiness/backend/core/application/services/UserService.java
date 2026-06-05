package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.locations.AddressDbPort;
import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.application.ports.shoppingcarts.ShoppingCartDbPort;
import com.healfiness.backend.core.application.ports.users.UserDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.users.UserCreateRequest;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.util.GlobalUserMapper;
import com.healfiness.backend.shared.util.SortOrderMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserDbPort userDbPort;

    private final AddressDbPort addressDbPort;

    private final OrderDbPort orderDbPort;

    private final ShoppingCartDbPort shoppingCartDbPort;

    private final GlobalUserMapper globalUserMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserDbPort userDbPort,
            AddressDbPort addressDbPort,
            OrderDbPort orderDbPort,
            ShoppingCartDbPort shoppingCartDbPort,
            GlobalUserMapper globalUserMapper,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder
    ) {
        this.userDbPort = userDbPort;
        this.addressDbPort = addressDbPort;
        this.orderDbPort = orderDbPort;
        this.shoppingCartDbPort = shoppingCartDbPort;
        this.globalUserMapper = globalUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public PageResponse<UserResponse> findAllUsers(
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Page<User> allFoundFlatUsers = userDbPort.findAllUsersFlat(page, size, sortOrders);

        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : allFoundFlatUsers) {
            user.setAddresses(addressDbPort.findAddressesByUserId(
                    user.getUsersId(),
                    null,
                    null,
                    Collections.emptyList()
            ).getContent());
            user.setOrders(orderDbPort.findOrdersByUserId(
                    user.getUsersId(),
                    null,
                    null,
                    Collections.emptyList()
            ).getContent());
            user.setShoppingCarts(shoppingCartDbPort.findShoppingCartsByUserId(user.getUsersId()));

            userResponses.add(globalUserMapper.toUserResponse(user));
        }
        return new PageResponse<>(userResponses,
                                  new PageMetaData(
                                          allFoundFlatUsers.getNumber(),
                                          allFoundFlatUsers.getSize(),
                                          allFoundFlatUsers.getTotalElements(),
                                          allFoundFlatUsers.getTotalPages(),
                                          allFoundFlatUsers.isLast(),
                                          SortOrderMapper.mapToDomainSort(allFoundFlatUsers.getSort())
                                  )
        );
    }

    public UserResponse findUserById(Long usersId) {
        User foundUser = userDbPort.findById(usersId);
        if (foundUser != null) {
            return globalUserMapper.toUserResponse(foundUser);
        } else {
            throw new ResourceNotFoundException("User with id " + usersId + " not found");
        }
    }

    public UserResponse createUser(@Valid UserCreateRequest userCreateRequest) {
        User userToCreate = new User();
        userToCreate.setUserName(userCreateRequest.userName());
        userToCreate.setPassword(passwordEncoder.encode(userCreateRequest.password()));
        userToCreate.setEmail(userCreateRequest.email());
        userToCreate.setFirstName(userCreateRequest.firstName() != null ? userCreateRequest.firstName() : null);
        userToCreate.setLastName(userCreateRequest.lastName() != null ? userCreateRequest.lastName() : null);
        userToCreate.setPhoneNumber(userCreateRequest.phoneNumber());
        userToCreate.setRole(userCreateRequest.role());

        User savedUser = userDbPort.save(userToCreate);

        return globalUserMapper.toUserResponse(savedUser);
    }
}
