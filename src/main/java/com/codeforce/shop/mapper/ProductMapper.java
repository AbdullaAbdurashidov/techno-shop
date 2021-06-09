package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.Product;
import com.codeforce.shop.dto.ProductDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{

    Product toEntity(ProductDTO dto);

    ProductDTO toDto(Product entity);

    List<Product> toEntity(List<ProductDTO> dtoList);

    List <ProductDTO> toDto(List<Product> entityList);

    default Product from(String id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }

}
