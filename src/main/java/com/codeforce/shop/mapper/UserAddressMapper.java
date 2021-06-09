package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.UserAddress;
import com.codeforce.shop.dto.UserAddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAddressMapper extends EntityMapper<UserAddressDTO, UserAddress>{

    UserAddressDTO toDto(UserAddress userAddress);

    UserAddress toEntity(UserAddressDTO userAddressDTO);

    List<UserAddressDTO> toDto(List<UserAddress> list);

    List<UserAddress> toEntity(List<UserAddressDTO> list);

    default UserAddress from(String id) {
        if (id == null) {
            return null;
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(id);
        return userAddress;
    }

}
