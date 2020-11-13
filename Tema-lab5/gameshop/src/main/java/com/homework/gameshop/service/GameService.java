package com.homework.gameshop.service;

import com.homework.gameshop.domain.Game;
import com.homework.gameshop.dto.GameDto;
import com.homework.gameshop.mapper.GameMapper;
import com.homework.gameshop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameMapper gameMapper, GameRepository gameRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    public List<GameDto> getAllGames() {
        return gameRepository.getAllGames().stream().map(gameMapper::convertGameDtoFrom).collect(Collectors.toList());
    }

    public GameDto getGameById(String id) {
        return gameMapper.convertGameDtoFrom(gameRepository.getGameById(id).get());
    }

    public void createGame(GameDto gameDto) {
        gameRepository.saveGame(gameMapper.convertGameFrom(gameDto));
    }

    public void updateGame(GameDto gameDto) {
        Game found = gameRepository.getGameById(gameDto.getGameId()).get();
        GameDto foundDto = gameMapper.convertGameDtoFrom(found);
        gameRepository.deleteGame(found);
        gameRepository.saveGame(gameMapper.convertGameFrom(gameDto));

    }

    public void deleteGame(String id) {
        Game found = gameRepository.getGameById(id).get();
        gameRepository.deleteGame(found);
    }
}
