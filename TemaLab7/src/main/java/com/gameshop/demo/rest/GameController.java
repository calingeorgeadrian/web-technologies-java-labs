package com.gameshop.demo.rest;

import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameDto> getAll() {
        return gameService.getAll();
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> create(@Valid @RequestBody GameDto request) {
        return new ResponseEntity<>(gameService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> update(@RequestBody GameDto request) {
        return new ResponseEntity<>(gameService.update(request), gameService.update(request) != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable String id) {
        return gameService.delete(id);
    }
}