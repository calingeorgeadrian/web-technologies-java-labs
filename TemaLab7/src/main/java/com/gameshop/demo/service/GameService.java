package com.gameshop.demo.service;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.mapper.GameMapper;
import com.gameshop.demo.repository.GameRepository;
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
    public GameDto get(String id) {
        return gameMapper.mapToDto(gameRepository.get(id));
    }

    public List<GameDto> getAll() {
        return gameRepository.getAll().stream()
                .map(game -> gameMapper.mapToDto(game))
                .collect(Collectors.toList());
    }

    public GameDto create(GameDto request) {
        Game savedGame = gameRepository.save(gameMapper.mapToEntity(request));
        return gameMapper.mapToDto(savedGame);
    }

    public GameDto update(GameDto request) {
        Game updatedGame = gameRepository.update(gameMapper.mapToEntity(request));
        if (updatedGame != null) {
            return gameMapper.mapToDto(updatedGame);
        }
        return null;
    }

    public String delete(String id) {
        return gameRepository.delete(id);
    }
}
