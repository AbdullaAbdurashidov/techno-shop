package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.UserPayment;
import com.codeforce.shop.dto.UserPaymentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPaymentMapper extends EntityMapper<UserPaymentDTO, UserPayment> {

    UserPaymentDTO toDto(UserPayment userPayment);

    UserPayment toEntity(UserPaymentDTO userPaymentDTO);

    List<UserPayment> toEntity(List<UserPaymentDTO> dtoList);

    List<UserPaymentDTO> toDto(List<UserPayment> entityList);

    default UserPayment from(String id) {
        if (id == null) {
            return null;
        }
        UserPayment userPayment = new UserPayment();
        userPayment.setUserId(id);
        return userPayment;
    }

}
