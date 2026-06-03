package com.healfiness.backend.core.domain.dto.products;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductPageResponse")
public class ProductPageResponse extends PageResponse<ProductResponse> {

    @Schema(implementation = ProductResponse.class)
    @Override
    public void setContent(java.util.List<ProductResponse> content) {
        super.setContent(content);
    }

    @Override
    public java.util.List<ProductResponse> getContent() {
        return super.getContent();
    }
}
