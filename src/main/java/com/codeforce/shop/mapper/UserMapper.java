package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.User;
import com.codeforce.shop.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);

    List<User> toEntity(List<UserDTO> dtoList);

    List <UserDTO> toDto(List<User> entityList);

    default User from(String id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

}
