package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.WishlistItemDto;
import com.bgjshop.backend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WishlistItemDto> get(@PathVariable String userId) {
        return wishlistService.get(userId);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WishlistItemDto> add(@Valid @RequestBody WishlistItemDto request) {
        wishlistService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/remove/userId={userId}&gameId={gameId}")
    public void removeGame(@PathVariable String userId, @PathVariable Long gameId) {
        wishlistService.removeGame(userId, gameId);
    }

    @DeleteMapping(path = "/clear/{userId}")
    public void empty(@PathVariable String userId) {
        wishlistService.clear(userId);
    }
}