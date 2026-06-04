package com.healfiness.backend.core.domain.dto.locations;

import com.healfiness.backend.core.domain.dto.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "AddressPageResponse")
public class AddressPageResponse extends PageResponse<AddressResponse> {

    @Override
    public void setContent(List<AddressResponse> content) {
        super.setContent(content);
    }

    @Schema(implementation = AddressResponse.class)
    @Override
    public List<AddressResponse> getContent() {
        return super.getContent();
    }
}
