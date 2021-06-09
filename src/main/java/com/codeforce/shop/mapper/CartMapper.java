package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.Cart;
import com.codeforce.shop.dto.CartDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart>{

    CartDTO toDto(Cart item);

    Cart toEntity(CartDTO dto);

    List<CartDTO> toDto(List<Cart> entityList);

    List<Cart> toEntity(List<CartDTO> dtoList);

    default Cart from(Long id) {
        if (id == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }
}
