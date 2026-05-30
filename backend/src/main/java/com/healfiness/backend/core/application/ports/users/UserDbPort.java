package com.healfiness.backend.core.application.ports.users;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import com.healfiness.backend.core.domain.entities.users.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserDbPort {
    PageResponse<UserResponse> findAllUsers(int page, int size, List<SortOrder> sortOrders);

    Page<User> findAllUsersFlat(Integer page, Integer size, List<SortOrder> sortOrders);

    User findById(Long usersId);
}
