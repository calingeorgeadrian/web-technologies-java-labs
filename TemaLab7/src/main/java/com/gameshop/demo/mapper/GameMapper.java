package com.gameshop.demo.mapper;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.dto.GameDto;
import org.mapstruct.Mapper;

@Mapper()
public interface GameMapper {

    GameDto mapToDto(Game game);

    Game mapToEntity(GameDto gameDto);

}