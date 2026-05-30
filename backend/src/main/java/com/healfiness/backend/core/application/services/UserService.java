package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.locations.AddressDbPort;
import com.healfiness.backend.core.application.ports.orders.OrderDbPort;
import com.healfiness.backend.core.application.ports.shoppingcarts.ShoppingCartDbPort;
import com.healfiness.backend.core.application.ports.users.UserDbPort;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.shared.util.GlobalUserMapper;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserDbPort userDbPort;

    private final AddressDbPort addressDbPort;

    private final OrderDbPort orderDbPort;

    private final ShoppingCartDbPort shoppingCartDbPort;

    private final GlobalUserMapper globalUserMapper;

    public UserService(
            UserDbPort userDbPort,
            AddressDbPort addressDbPort,
            OrderDbPort orderDbPort,
            ShoppingCartDbPort shoppingCartDbPort,
            GlobalUserMapper globalUserMapper
    ) {
        this.userDbPort = userDbPort;
        this.addressDbPort = addressDbPort;
        this.orderDbPort = orderDbPort;
        this.shoppingCartDbPort = shoppingCartDbPort;
        this.globalUserMapper = globalUserMapper;
    }

    public PageResponse<UserResponse> findAllUsers(
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Page<User> allFoundFlatUsers = userDbPort.findAllUsersFlat(page, size, sortOrders);

        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : allFoundFlatUsers) {
            user.setAddresses(addressDbPort.findAddressesByUserId(user.getUsersId()));
            user.setOrders(orderDbPort.findOrdersByUserId(user.getUsersId()));
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

    public UserResponse findById(Long usersId) throws Exception {
        User foundUser = userDbPort.findById(usersId);
        if (foundUser != null) {
            return globalUserMapper.toUserResponse(foundUser);
        } else {
            throw new Exception("User with id " + usersId + " not found");
        }
    }
}
