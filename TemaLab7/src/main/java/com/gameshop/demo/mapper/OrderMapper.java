package com.gameshop.demo.mapper;

import com.gameshop.demo.domain.Order;
import com.gameshop.demo.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = GameMapper.class)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.orderId"),
            @Mapping(target = "date", source = "dto.orderDate"),
            @Mapping(target = "customerName", source = "dto.orderCustomerName"),
            @Mapping(target = "address", source = "dto.orderAddress"),
            @Mapping(target = "gameList", source = "dto.orderGameList")
    })
    Order mapToEntity(OrderDto dto);

    @Mappings({
            @Mapping(target = "orderId", source = "entity.id"),
            @Mapping(target = "orderDate", source = "entity.date"),
            @Mapping(target = "orderCustomerName", source = "entity.customerName"),
            @Mapping(target = "orderAddress", source = "entity.address"),
            @Mapping(target = "orderGameList", source = "entity.gameList")
    })
    OrderDto mapToDto(Order entity);
}
