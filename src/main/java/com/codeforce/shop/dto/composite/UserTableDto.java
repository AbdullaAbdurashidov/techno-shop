package com.codeforce.shop.dto.composite;

import com.codeforce.shop.dto.FileDTO;
import com.codeforce.shop.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTableDto {

    private UserDTO userDto;

    private FileDTO fileDto;

}
