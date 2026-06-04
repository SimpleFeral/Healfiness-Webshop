package com.healfiness.backend.core.domain.dto.orders;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "OrderPageResponse")
public class OrderPageResponse extends PageResponse<OrderResponse> {

    @Schema(implementation = OrderResponse.class)
    @Override
    public List<OrderResponse> getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(List<OrderResponse> content) {
        super.setContent(content);
    }
}
