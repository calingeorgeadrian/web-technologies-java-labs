package com.homework.gameshop.controller;

import com.homework.gameshop.dto.GameDto;
import com.homework.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<GameDto> gameDtos = gameService.getAllGames();
        model.addAttribute("gameDtos", gameDtos);
        return "view-games";
    }

    @GetMapping("/{id}")
    public String getGameById(@PathVariable("id") String id, Model model) {
        GameDto gameDto = gameService.getGameById(id);
        model.addAttribute("gameDto", gameDto);
        return "view-game";
    }

    @GetMapping("/view-create")
    public String viewCreate(GameDto gameDto) {
        return "add-game";
    }

    @PostMapping("/create")
    public String createGame(@Valid GameDto gameDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-game";
        }
        gameService.createGame(gameDto);
        List<GameDto> gameDtos = gameService.getAllGames();
        model.addAttribute("gameDtos", gameDtos);
        return "view-games";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable String id, GameDto gameDto, Model model) {
        GameDto foundGameDto = gameService.getGameById(id);
        model.addAttribute("gameDto", foundGameDto);
        return "update-game";
    }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable String id, @Valid GameDto gameDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-game";
        }
        gameService.updateGame(gameDto);
        model.addAttribute("gameDto", gameService.getGameById(id));
        return "view-game";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        gameService.deleteGame(id);
        List<GameDto> gameDtos = gameService.getAllGames();
        model.addAttribute("gameDtos", gameDtos);
        return "view-games";
    }
}
