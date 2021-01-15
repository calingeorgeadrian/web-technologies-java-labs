package com.gameshop.demo.mapper;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.gameId"),
            @Mapping(target = "title", source = "dto.gameTitle"),
            @Mapping(target = "description", source = "dto.gameDescription"),
            @Mapping(target = "price", source = "dto.gamePrice"),
            @Mapping(target = "stock", source = "dto.gameStock")
    })
    Game mapToEntity(GameDto dto);

    @Mappings({
            @Mapping(target = "gameId", source = "entity.id"),
            @Mapping(target = "gameTitle", source = "entity.title"),
            @Mapping(target = "gameDescription", source = "entity.description"),
            @Mapping(target = "gamePrice", source = "entity.price"),
            @Mapping(target = "gameStock", source = "entity.stock")
    })
    GameDto mapToDto(Game entity);
}