package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.Game;
import com.bgjshop.backend.domain.Promotion;
import com.bgjshop.backend.dto.GameDto;
import com.bgjshop.backend.dto.PopularGameDto;
import com.bgjshop.backend.dto.PromotionDto;
import com.bgjshop.backend.exceptions.DuplicateEntityException;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import com.bgjshop.backend.mapper.GameMapper;
import com.bgjshop.backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private final PromotionService promotionService;

    @Autowired
    public GameService(GameMapper gameMapper, GameRepository gameRepository, PromotionService promotionService) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.promotionService = promotionService;
    }

    public GameDto get(Long id) {
        GameDto game = gameRepository.get(id).orElseThrow(()-> new EntityNotFoundException("Game"));
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null)
            game.setNewPrice(game.getPrice() - game.getPrice() * activePromotion.getDiscount()/100);
        else
            game.setNewPrice(game.getPrice());

        return game;
    }

    public List<GameDto> getAll() {
        List<GameDto> games = gameRepository.getAll();
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null)
            games.forEach(game -> game.setNewPrice(game.getPrice() - game.getPrice() * activePromotion.getDiscount()/100));
        else
            games.forEach(game -> game.setNewPrice(game.getPrice()));

        return games;
    }

    public List<GameDto> search(String term) {
        List<GameDto> games = gameRepository.search(term);
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null)
            games.forEach(game -> game.setNewPrice(game.getPrice() - game.getPrice() * activePromotion.getDiscount()/100));
        else
            games.forEach(game -> game.setNewPrice(game.getPrice()));

        return games;
    }

    public List<GameDto> filter(String type, Integer minPrice, Integer maxPrice, Integer minPlayers, Integer maxPlayers) {
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null) {
            float discount = (float)activePromotion.getDiscount()/100;
            List<GameDto> games =  gameRepository.filter(type, minPrice, maxPrice, minPlayers, maxPlayers, 1-discount);
            games.forEach(game -> game.setNewPrice(game.getPrice() * (1-discount)));

            return games;
        }
        else {
            List<GameDto> games =  gameRepository.filter(type, minPrice, maxPrice, minPlayers, maxPlayers,null);
            games.forEach(game -> game.setNewPrice(game.getPrice()));

            return games;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(GameDto request) {
        Optional<GameDto> gameDtoOptional = gameRepository.getByBggId(request.getBggId());
        if(gameDtoOptional.isPresent())
            throw new DuplicateEntityException("Game with BggId " + request.getBggId());
        Game game = gameMapper.toEntity(request);
        gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
    public void importGames(List<GameDto> requests) {
        List<Game> games = gameMapper.toEntityList(requests);
        gameRepository.importGames(games);
    }

    public void update(GameDto request) {
        Game game = gameMapper.toEntity(request);
        gameRepository.update(game);
    }

    public void delete(Long id) {
        gameRepository.delete(id);
    }
}
