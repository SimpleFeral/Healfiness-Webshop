package com.healfiness.backend.core.application.ports.locations;

import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.locations.Address;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressDbPort {
    Page<Address> findAddressesByUserId(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    );

    Address findById(Long addressId);

    Address save(Address addressToCreate);

}
