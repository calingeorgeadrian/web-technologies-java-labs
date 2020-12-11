package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

    private final List<Game> gameList = new ArrayList<>();

    public GameRepository() {
        setContextForGameRepository();
    }

    public Game get(String id) {
        return gameList.stream().filter(game -> game.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s could not be found", id)));
    }

    public List<Game> getAll() {
        return gameList;
    }

    public Game save(Game request) {
        gameList.add(request);
        return request;
    }

    public String delete(String id) {
        Game gameOptional = get(id);
        if (gameOptional != null) {
            gameList.remove(gameOptional);
            return "Removed!";
        }
        return "Game not game!";

    }

    public Game update(Game request) {
        Game gameOptional = get(request.getId());
        if (gameOptional != null) {
            gameList.remove(gameOptional);
            gameList.add(request);
            return get(request.getId());
        }
        return null;
    }

    private void setContextForGameRepository() {
        for (int i = 0; i < 7; i++) {
            gameList.add(Game.builder()
                    .id("gm" + i)
                    .title("Game " + i)
                    .description("Description of game " + i)
                    .price((int)(Math.random() * 100))
                    .stock((int)(Math.random() * 5))
                    .build());
        }
    }
}
