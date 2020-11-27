package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.domain.Order;
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

    public Optional<Order> get(String id) {
        return orderList.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    public List<Order> getAll() {
        return orderList;
    }

    public Order save(Order request) {
        orderList.add(request);
        return request;
    }

    public String delete(String id) {
        Optional<Order> orderOptional = get(id);
        if (orderOptional.isPresent()) {
            orderList.remove(orderOptional.get());
            return "Removed!";
        }
        return "Order not order!";

    }

    public Order update(Order request) {
        Optional<Order> orderOptional = get(request.getId());
        if (orderOptional.isPresent()) {
            orderList.remove(orderOptional.get());
            orderList.add(request);
            return get(request.getId()).get();
        }
        return null;
    }

    private List<Game> getGamesForOrder(int orderIndex) {
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < orderIndex+1; i++) {
            Optional<Game> gameById = gameRepository.get("gm" + i);
            gameById.ifPresent(games::add);
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
