package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.Order;
import com.codeforce.shop.dto.OrderDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDto, Order> {

    Order toEntity(OrderDto dto);

    OrderDto toDto(Order entity);

    List<Order> toEntity(List<OrderDto> dtoList);

    List<OrderDto> toDto(List<Order> entityList);

    default Order from(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }

}
