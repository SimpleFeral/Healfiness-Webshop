package com.healfiness.backend.shared.util;

import com.healfiness.backend.core.domain.dto.page.SortOrder;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public final class SortOrderMapper {

    private SortOrderMapper() {
    }

    public static Sort mapToSpringSort(List<SortOrder> domainOrders) {
        if (domainOrders == null || domainOrders.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = domainOrders.stream()
                .map(order -> new Sort.Order(
                        Sort.Direction.fromString(order.direction().name()),
                        order.property()
                ))
                .toList();

        return Sort.by(orders);
    }

    public static List<SortOrder> mapToDomainSort(Sort sort) {
        if (sort == null || sort.isEmpty()) {
            return Collections.emptyList();
        }

        return sort.stream().map(sortArgument -> new SortOrder(
                        sortArgument.getProperty(),
                        SortOrder.Direction.getByValue(sortArgument.getDirection().name()))
                )
                .toList();
    }
}
