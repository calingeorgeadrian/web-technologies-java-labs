package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.UserDto;
import com.bgjshop.backend.dto.UserLoginDto;
import com.bgjshop.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto get(@PathVariable String id) {
        return userService.get(id);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginDto> register(@Valid @RequestBody UserLoginDto request) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@Valid @RequestBody UserLoginDto request) {
        return userService.login(request);
    }

    @PutMapping(path = "/loginAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto loginAdmin(@Valid @RequestBody UserLoginDto request) {
        return userService.loginAdmin(request);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserDto request) {
        userService.update(request);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
