package com.gameshop.demo.service;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.mapper.GameMapper;
import com.gameshop.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return gameRepository.get(id);
    }

    public List<GameDto> getAll() {
        return gameRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(GameDto request) {
        Game game = gameMapper.mapToEntity(request);
        gameRepository.save(game);
    }

    public void update(Long id, String title) {
        gameRepository.update(id, title);
    }

    public void delete(String id) {
        gameRepository.delete(id);
    }
}
