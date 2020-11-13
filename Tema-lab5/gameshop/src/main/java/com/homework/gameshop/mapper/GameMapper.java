package com.homework.gameshop.mapper;

import com.homework.gameshop.domain.Game;
import com.homework.gameshop.dto.GameDto;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public Game convertGameFrom(GameDto gameDto) {
        return Game.builder()
                .id(gameDto.getGameId())
                .title(gameDto.getGameTitle())
                .description(gameDto.getGameDescription())
                .price(gameDto.getGamePrice())
                .minPlayers(gameDto.getGameMinPlayers())
                .maxPlayers(gameDto.getGameMaxPlayers())
                .build();
    }

    public GameDto convertGameDtoFrom(Game game) {
        return GameDto.builder()
                .gameId(game.getId())
                .gameTitle(game.getTitle())
                .gameDescription(game.getDescription())
                .gamePrice(game.getPrice())
                .gameMinPlayers(game.getMinPlayers())
                .gameMaxPlayers(game.getMaxPlayers())
                .build();
    }
}
