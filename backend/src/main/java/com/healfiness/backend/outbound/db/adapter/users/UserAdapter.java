package com.healfiness.backend.outbound.db.adapter.users;

import com.healfiness.backend.core.application.ports.users.UserDbPort;
import com.healfiness.backend.core.domain.dto.exceptions.ResourceNotFoundException;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.users.UserFlatDatabaseProjection;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.outbound.db.jpa.users.UserRepository;
import com.healfiness.backend.outbound.db.jpaentities.locations.AddressEntity;
import com.healfiness.backend.outbound.db.jpaentities.orders.OrderEntity;
import com.healfiness.backend.outbound.db.jpaentities.shoppingcarts.ShoppingCartEntity;
import com.healfiness.backend.shared.util.GlobalUserMapper;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserAdapter implements UserDbPort {

    private final UserRepository userRepository;

    private final GlobalUserMapper mapper;

    public UserAdapter(
            UserRepository userRepository,
            GlobalUserMapper mapper
    ) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public PageResponse<UserResponse> findAllUsers(int page, int size, List<SortOrder> sortOrders) {
        Pageable pageable = PageRequest.of(page, size, SortOrderMapper.mapToSpringSort(sortOrders));

        Page<UserFlatDatabaseProjection> flatUsers = userRepository.findAllFlat(pageable);
        if (flatUsers == null || flatUsers.isEmpty()) {
            return new PageResponse<>(Collections.emptyList(), new PageMetaData(
                    pageable.getPageNumber(), pageable.getPageSize(),
                    0L, 0, true, Collections.emptyList()));
        }

        List<Long> userIds = flatUsers.stream()
                .map(UserFlatDatabaseProjection::usersId)
                .toList();

        List<AddressEntity> allAddresses = userRepository.findAddressesByUserIds(userIds);
        List<OrderEntity> allOrders = userRepository.findOrdersByUserIds(userIds);
        List<ShoppingCartEntity> allShoppingCarts = userRepository.findShoppingCartsByUserIds(userIds);

        var userAddresses = allAddresses.stream().collect(
                Collectors.groupingBy(a -> a.getUser().getUsersId()));
        var userOrders = allOrders.stream().collect(Collectors.groupingBy(a -> a.getUser().getUsersId()));
        var userShoppingCarts = allShoppingCarts.stream().collect(
                Collectors.groupingBy(a -> a.getUser().getUsersId()));

        List<UserResponse> content = flatUsers.getContent().stream().map(flatUser -> {
            Long userId = flatUser.usersId();

            return mapper.toDomain(flatUser,
                    mapper.toAddressDomain(userAddresses.getOrDefault(userId, Collections.emptyList())),
                    mapper.toOrderDomain(userOrders.getOrDefault(userId, Collections.emptyList())),
                    mapper.toShoppingCartDomain(userShoppingCarts.getOrDefault(userId, Collections.emptyList()))
            );
        }).toList();

        return new PageResponse<>(
                content,
                new PageMetaData(
                        flatUsers.getNumber(),
                        flatUsers.getSize(),
                        flatUsers.getTotalElements(),
                        flatUsers.getTotalPages(),
                        flatUsers.isLast(),
                        SortOrderMapper.mapToDomainSort(flatUsers.getSort()))
        );
    }

    @Override
    public Page<User> findAllUsersFlat(Integer page, Integer size, List<SortOrder> sortOrders) {
        Pageable pageable = PageRequest.of(page, size, SortOrderMapper.mapToSpringSort(sortOrders));
        return userRepository.findAllUsersFlat(pageable);
    }

    @Override
    public User findById(Long usersId) {
        return mapper.toDomainEntity(
                userRepository.findById(usersId)
                        .orElseThrow(() -> new ResourceNotFoundException("User with id " + usersId + " not found"))
        );
    }

    @Override
    public User save(User userToCreate) {
        return mapper.toDomainEntity(userRepository.save(mapper.toJpaEntity(userToCreate)));
    }
}
