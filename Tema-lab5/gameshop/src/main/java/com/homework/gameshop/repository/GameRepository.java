package com.homework.gameshop.repository;

import com.homework.gameshop.domain.Game;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    private final List<Game> gameList = new ArrayList<>();

    public List<Game> getAllGames() {
        return gameList;
    }

    public Optional<Game> getGameById(String id) {
        return gameList.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

    public void saveGame(Game game) {
        gameList.add(game);
    }

    public void deleteGame(Game game){
        gameList.remove(game);
    }

    private void createAndSave(String id, String name, String description, int price, int minPlayers, int maxPlayers) {
        Game newGame = Game.builder()
                .id(id)
                .title(name)
                .description(description)
                .price(price)
                .minPlayers(maxPlayers)
                .build();

        gameList.add(newGame);
    }

    private void setContextForGameRepository() {
        for (int i = 0; i < 5; i++) {
            int minPlayers = (int) (Math.random() * 2);
            int maxPlayers = minPlayers + 2;
            createAndSave(  "gm" + i, "Game " + i, "Description of game " + i, (int) (Math.random() * 100), minPlayers, maxPlayers);
        }
    }

    @PostConstruct
    public void afterPropertiesSetting() {
        setContextForGameRepository();
    }
}
