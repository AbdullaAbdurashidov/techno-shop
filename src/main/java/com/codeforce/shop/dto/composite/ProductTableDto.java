package com.codeforce.shop.dto.composite;

import com.codeforce.shop.dto.FileDTO;
import com.codeforce.shop.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTableDto {

    private ProductDTO productDto;

    private FileDTO fileDto;

}
