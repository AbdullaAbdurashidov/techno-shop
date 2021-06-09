package com.codeforce.shop.dto.composite;

import com.codeforce.shop.dto.composite.ProductTableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartDto {

    private ProductTableDto productTable;

    private Integer quantity;

}
