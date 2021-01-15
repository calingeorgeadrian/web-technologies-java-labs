package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.CartItemDto;
import com.bgjshop.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CartItemDto>  get(@PathVariable String userId) {
        return cartService.get(userId);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartItemDto> add(@Valid @RequestBody CartItemDto request) {
        cartService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateItem(@Valid @RequestBody CartItemDto request) {
        cartService.updateItem(request);
    }

    @DeleteMapping(path = "/remove/userId={userId}&gameId={gameId}")
    public void removeGame(@PathVariable String userId, @PathVariable Long gameId) {
        cartService.removeGame(userId, gameId);
    }

    @DeleteMapping(path = "/empty/{userId}")
    public void empty(@PathVariable String userId) {
        cartService.empty(userId);
    }
}
