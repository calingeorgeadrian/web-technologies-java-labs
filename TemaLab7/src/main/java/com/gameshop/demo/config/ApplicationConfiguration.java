package com.gameshop.demo.config;

import com.gameshop.demo.mapper.GameMapper;
import com.gameshop.demo.mapper.GameMapperImpl;
import com.gameshop.demo.mapper.OrderMapper;
import com.gameshop.demo.mapper.OrderMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GameMapper gameMapper(){
        return new GameMapperImpl() {
        };
    }

    @Bean
    public OrderMapper orderMapper(){
        return new OrderMapperImpl();
    }
}