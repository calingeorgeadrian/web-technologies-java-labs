package com.bgjshop.backend.mapper;

import com.bgjshop.backend.domain.Game;
import com.bgjshop.backend.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "bggId", source = "dto.bggId"),
            @Mapping(target = "title", source = "dto.title"),
            @Mapping(target = "type", source = "dto.type"),
            @Mapping(target = "imageUrl", source = "dto.imageUrl"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "minPlayers", source = "dto.minPlayers"),
            @Mapping(target = "maxPlayers", source = "dto.maxPlayers"),
            @Mapping(target = "playingTime", source = "dto.playingTime"),
            @Mapping(target = "year", source = "dto.year"),
            @Mapping(target = "price", source = "dto.price"),
            @Mapping(target = "stock", source = "dto.stock"),
            @Mapping(target = "dateAdded", source = "dto.dateAdded"),
            @Mapping(target = "dateModified", source = "dto.dateModified")
    })
    Game toEntity(GameDto dto);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "bggId", source = "entity.bggId"),
            @Mapping(target = "title", source = "entity.title"),
            @Mapping(target = "type", source = "entity.type"),
            @Mapping(target = "imageUrl", source = "entity.imageUrl"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "minPlayers", source = "entity.minPlayers"),
            @Mapping(target = "maxPlayers", source = "entity.maxPlayers"),
            @Mapping(target = "playingTime", source = "entity.playingTime"),
            @Mapping(target = "year", source = "entity.year"),
            @Mapping(target = "price", source = "entity.price"),
            @Mapping(target = "stock", source = "entity.stock"),
            @Mapping(target = "dateAdded", source = "entity.dateAdded"),
            @Mapping(target = "dateModified", source = "entity.dateModified")
    })
    GameDto toDto(Game entity);

    List<Game> toEntityList(List<GameDto> games);
    List<GameDto> toDtoList(List<Game> games);
}