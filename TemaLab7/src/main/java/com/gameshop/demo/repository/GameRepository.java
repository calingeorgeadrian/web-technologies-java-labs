package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    private final List<Game> gameList = new ArrayList<>();

    public GameRepository() {
        setContextForGameRepository();
    }

    public Optional<Game> get(String id) {
        return gameList.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

    public List<Game> getAll() {
        return gameList;
    }

    public Game save(Game request) {
        gameList.add(request);
        return request;
    }

    public String delete(String id) {
        Optional<Game> gameOptional = get(id);
        if (gameOptional.isPresent()) {
            gameList.remove(gameOptional.get());
            return "Removed!";
        }
        return "Game not game!";

    }

    public Game update(Game request) {
        Optional<Game> gameOptional = get(request.getId());
        if (gameOptional.isPresent()) {
            gameList.remove(gameOptional.get());
            gameList.add(request);
            return get(request.getId()).get();
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
                    .build());
        }
    }
}
