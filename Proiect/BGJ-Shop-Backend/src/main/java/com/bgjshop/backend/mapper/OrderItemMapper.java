package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.OrderItem;
import com.bgjshop.backend.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mappings({
            @Mapping(target = "orderId", source = "dto.orderId"),
            @Mapping(target = "gameId", source = "dto.gameId"),
            @Mapping(target = "quantity", source = "dto.quantity"),
            @Mapping(target = "price", source = "dto.price")
    })
    OrderItem toEntity(OrderItemDto dto);

    @Mappings({
            @Mapping(target = "orderId", source = "entity.orderId"),
            @Mapping(target = "gameId", source = "entity.gameId"),
            @Mapping(target = "quantity", source = "entity.quantity"),
            @Mapping(target = "price", source = "entity.price")
    })
    OrderItemDto toDto(OrderItem entity);

    List<OrderItem> toEntityList(List<OrderItemDto> items);
    List<OrderItemDto> toDtoList(List<OrderItem> items);
}
