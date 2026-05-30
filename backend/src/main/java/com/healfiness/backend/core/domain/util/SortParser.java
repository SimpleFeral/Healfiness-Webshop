package com.healfiness.backend.core.domain.util;

import com.healfiness.backend.core.domain.dto.page.SortOrder;

import java.util.Collections;
import java.util.List;

public final class SortParser {

    private SortParser() {}

    public static List<SortOrder> parse(List<String> sortParams) {
        if (sortParams == null || sortParams.isEmpty()) {
            return Collections.emptyList();
        }

        return sortParams.stream().map(SortParser::convertToSortOrder).toList();
    }

    private static SortOrder convertToSortOrder(String param) {
        String[] split = param.split(",");
        String property = split[0].trim();

        SortOrder.Direction direction = SortOrder.Direction.ASC;
        if (split.length > 1) {
            direction = SortOrder.Direction.valueOf(split[1].trim().toUpperCase());
        }
        return new SortOrder(property, direction);
    }
}
