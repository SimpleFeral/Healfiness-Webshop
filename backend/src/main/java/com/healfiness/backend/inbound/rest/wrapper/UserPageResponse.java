package com.healfiness.backend.inbound.rest.wrapper;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.users.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "UserPageResponse")
public class UserPageResponse extends PageResponse<UserResponse> {

    @Schema(implementation = UserResponse.class)
    @Override
    public List<UserResponse> getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(List<UserResponse> content) {
        super.setContent(content);
    }
}
