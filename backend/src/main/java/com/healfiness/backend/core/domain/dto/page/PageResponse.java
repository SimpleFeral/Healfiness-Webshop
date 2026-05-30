package com.healfiness.backend.core.domain.dto.page;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema
public class PageResponse<T> {

    @Schema(type = "array", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> content;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private PageMetaData pageMetaData;

    public PageResponse() {
    }

    public PageResponse(
            List<T> content,
            PageMetaData pageMetaData
    ) {
        this.content = content;
        this.pageMetaData = pageMetaData;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageMetaData getPageMetaData() {
        return pageMetaData;
    }

    public void setPageMetaData(PageMetaData pageMetaData) {
        this.pageMetaData = pageMetaData;
    }
}
