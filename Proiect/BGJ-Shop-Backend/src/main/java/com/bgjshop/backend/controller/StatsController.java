package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.*;
import com.bgjshop.backend.service.GameService;
import com.bgjshop.backend.service.OrderService;
import com.bgjshop.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping(path = "/popularGames", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PopularGameDto> getPopularGames() {
        return statsService.getPopularGames();
    }

    @GetMapping(path = "/orderStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderStatsDto getOrderStats() {
        return statsService.getOrderStats();
    }

    @GetMapping(path = "/report/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportDto getReport(@PathVariable Integer type) {
        return statsService.getReport(type);
    }
}
