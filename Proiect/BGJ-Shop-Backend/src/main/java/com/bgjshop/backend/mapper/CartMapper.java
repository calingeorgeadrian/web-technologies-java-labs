package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.CartItem;
import com.bgjshop.backend.dto.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mappings({
            @Mapping(target = "userId", source = "dto.userId"),
            @Mapping(target = "gameId", source = "dto.gameId"),
            @Mapping(target = "quantity", source = "dto.quantity")
    })
    CartItem toEntity(CartItemDto dto);

    @Mappings({
            @Mapping(target = "userId", source = "entity.userId"),
            @Mapping(target = "gameId", source = "entity.gameId"),
            @Mapping(target = "quantity", source = "entity.quantity")
    })
    CartItemDto toDto(CartItem entity);

    List<CartItem> toEntityList(List<CartItemDto> items);
    List<CartItemDto> toDtoList(List<CartItem> items);
}
