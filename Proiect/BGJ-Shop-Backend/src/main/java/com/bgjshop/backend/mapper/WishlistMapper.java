package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.WishlistItem;
import com.bgjshop.backend.dto.WishlistItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    @Mappings({
            @Mapping(target = "userId", source = "dto.userId"),
            @Mapping(target = "gameId", source = "dto.gameId")
    })
    WishlistItem toEntity(WishlistItemDto dto);

    @Mappings({
            @Mapping(target = "userId", source = "entity.userId"),
            @Mapping(target = "gameId", source = "entity.gameId")
    })
    WishlistItemDto toDto(WishlistItem entity);
}
