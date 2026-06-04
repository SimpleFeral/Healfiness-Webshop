package com.healfiness.backend.core.application.services;

import com.healfiness.backend.core.application.ports.locations.AddressDbPort;
import com.healfiness.backend.core.domain.dto.locations.AddressCreateRequest;
import com.healfiness.backend.core.domain.dto.locations.AddressResponse;
import com.healfiness.backend.core.domain.dto.locations.city.CityResponse;
import com.healfiness.backend.core.domain.dto.locations.isoCountryCode.IsoCountryCodeResponse;
import com.healfiness.backend.core.domain.dto.page.PageMetaData;
import com.healfiness.backend.core.domain.dto.page.PageResponse;
import com.healfiness.backend.core.domain.dto.page.SortOrder;
import com.healfiness.backend.core.domain.entities.locations.Address;
import com.healfiness.backend.core.domain.entities.locations.City;
import com.healfiness.backend.core.domain.entities.locations.IsoCountryCodes;
import com.healfiness.backend.core.domain.entities.users.User;
import com.healfiness.backend.core.domain.util.AuditingMapper;
import com.healfiness.backend.shared.components.Schemas;
import com.healfiness.backend.shared.util.SortOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressDbPort addressDbPort;

    private final AuditingMapper auditingMapper;

    public AddressService(
            AddressDbPort addressDbPort,
            AuditingMapper auditingMapper
    ) {
        this.addressDbPort = addressDbPort;
        this.auditingMapper = auditingMapper;
    }

    public AddressResponse createAddressForCurrentUser(
            Long userId,
            AddressCreateRequest addressCreateRequest
    ) {
        User user = new User();
        user.setUsersId(userId);

        IsoCountryCodes isoCountryCodes = new IsoCountryCodes();
        isoCountryCodes.setIsoCountryCodesId(addressCreateRequest.city().isoCountryCodeId());

        City city = new City();
        city.setShortName(addressCreateRequest.city().shortName());
        city.setName(addressCreateRequest.city().name());
        city.setIsoCountryCode(isoCountryCodes);

        Address addressToCreate = new Address(
                addressCreateRequest.street(),
                addressCreateRequest.houseNumber(),
                addressCreateRequest.postalCode(),
                StringUtils.isNotBlank(addressCreateRequest.suffix())
                        ? addressCreateRequest.suffix()
                        : null,
                city,
                addressCreateRequest.type(),
                user
        );

        Address savedAddress = addressDbPort.save(addressToCreate);

        return mapToAddressResponse(savedAddress);
    }

    public AddressResponse findAddressById(Long addressId) {
        Address foundAddress = addressDbPort.findById(addressId);

        return mapToAddressResponse(foundAddress);
    }

    public AddressResponse mapToAddressResponse(Address addressToMap) {
        return new AddressResponse(
                new Schemas.Id(addressToMap.getAddressesId()),
                addressToMap.getStreet(),
                addressToMap.getHouseNumber(),
                addressToMap.getPostalCode(),
                addressToMap.getSuffix(),
                addressToMap.getAddressType(),
                new CityResponse(
                        new Schemas.Id(addressToMap.getCity().getCitiesId()),
                        addressToMap.getCity().getShortName(),
                        addressToMap.getCity().getName(),
                        new IsoCountryCodeResponse(
                                new Schemas.Id(addressToMap.getCity().getIsoCountryCode().getIsoCountryCodesId()),
                                addressToMap.getCity().getIsoCountryCode().getIsoCountryCode(),
                                addressToMap.getCity().getIsoCountryCode().getCountryName(),
                                auditingMapper.mapAuditingFields(addressToMap.getCity().getIsoCountryCode())
                        ),
                        auditingMapper.mapAuditingFields(addressToMap.getCity())
                ),
                new Schemas.Id(addressToMap.getUser().getUsersId()),
                auditingMapper.mapAuditingFields(addressToMap)
        );
    }

    public PageResponse<AddressResponse> findAddressesByCurrentUser(
            Long usersId,
            Integer page,
            Integer size,
            List<SortOrder> sortOrders
    ) {
        Page<Address> addresses = addressDbPort.findAddressesByCurrentUser(usersId, page, size, sortOrders);
        return new PageResponse<>(
                addresses.stream().map(this::mapToAddressResponse).toList(),
                new PageMetaData(
                        addresses.getNumber(),
                        addresses.getSize(),
                        addresses.getTotalElements(),
                        addresses.getTotalPages(),
                        addresses.isLast(),
                        SortOrderMapper.mapToDomainSort(addresses.getSort())
                )
        );
    }
}
