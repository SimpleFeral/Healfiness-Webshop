package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "ProductPageResponse")
public class ProductPageResponse extends PageResponse<ProductResponse> {

    @Override
    public void setContent(List<ProductResponse> content) {
        super.setContent(content);
    }

    @Schema(implementation = ProductResponse.class)
    @Override
    public List<ProductResponse> getContent() {
        return super.getContent();
    }
}
