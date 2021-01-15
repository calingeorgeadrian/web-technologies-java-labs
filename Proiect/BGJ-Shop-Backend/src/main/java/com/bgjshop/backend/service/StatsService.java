package com.bgjshop.backend.service;

import com.bgjshop.backend.dto.*;
import com.bgjshop.backend.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }


    public List<PopularGameDto> getPopularGames() {
        return statsRepository.getPopularGames();
    }
    public OrderStatsDto getOrderStats() {
        return statsRepository.getOrderStats();
    }
    public ReportDto getReport(Integer type) {
        String intervalType;
        switch(type) {
            case 1: {intervalType =  "1 DAY"; break;}
            case 2: {intervalType =  "7 DAY"; break;}
            case 3: {intervalType =  "1 MONTH"; break;}
            default: {intervalType = "1 DAY";}
        }
        return statsRepository.getReport(intervalType);
    }
}
