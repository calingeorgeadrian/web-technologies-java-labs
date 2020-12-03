package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.domain.Order;
import com.gameshop.demo.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final GameRepository gameRepository;
    private final List<Order> orderList = new ArrayList<>();

    @Autowired
    public OrderRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        setContextForOrderRepository();
    }

    public Order get(String id) {
        return orderList.stream().filter(order -> order.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s could not be found", id)));
    }

    public List<Order> getAll() {
        return orderList;
    }

    public Order save(Order request) {
        orderList.add(request);
        return request;
    }

    public String delete(String id) {
        Order orderOptional = get(id);
        if (orderOptional != null) {
            orderList.remove(orderOptional);
            return "Removed!";
        }
        return "Order not order!";

    }

    public Order update(Order request) {
        Order orderOptional = get(request.getId());
        if (orderOptional != null) {
            orderList.remove(orderOptional);
            orderList.add(request);
            return get(request.getId());
        }
        return null;
    }

    private List<Game> getGamesForOrder(int orderIndex) {
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < orderIndex+1; i++) {
            Game gameById = gameRepository.get("gm" + i);
            if(gameById != null) {
                games.add((gameById));
            }
        }
        return games;
    }

    private void setContextForOrderRepository() {
        for (int i = 0; i < 5; i++) {
            orderList.add(Order.builder()
                    .id("ord" + i)
                    .date("25-11-2020")
                    .customerName("Customer  " + i)
                    .address("Address of customer " + i)
                    .gameList(getGamesForOrder(i))
                    .build());
        }
    }
}
