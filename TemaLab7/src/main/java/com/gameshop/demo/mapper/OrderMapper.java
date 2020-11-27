package com.gameshop.demo.mapper;

import com.gameshop.demo.domain.Order;
import com.gameshop.demo.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper()
public interface OrderMapper {

    OrderDto mapToDto(Order order);

    Order mapToEntity(OrderDto orderDto);
}
