package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.GameDto;
import com.bgjshop.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> get(@PathVariable Long id) {
        GameDto game = gameService.get(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameDto> getAll() {
        return gameService.getAll();
    }

    @GetMapping(path = "/search/{term}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameDto> search(@PathVariable String term) {return gameService.search(term);
    }

    @GetMapping(path = "/filter/type={type}&minPrice={minPrice}&maxPrice={maxPrice}&minPlayers={minPlayers}&maxPlayers={maxPlayers}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameDto> filter(@PathVariable String type, @PathVariable Integer minPrice, @PathVariable Integer maxPrice, @PathVariable Integer minPlayers, @PathVariable Integer maxPlayers) {
        return gameService.filter(type, minPrice, maxPrice, minPlayers, maxPlayers);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> create(@Valid @RequestBody GameDto request) {
        gameService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/importGames", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> importGames(@Valid @RequestBody List<GameDto> requests) {
        gameService.importGames(requests);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody GameDto request) {
        gameService.update(request);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}
