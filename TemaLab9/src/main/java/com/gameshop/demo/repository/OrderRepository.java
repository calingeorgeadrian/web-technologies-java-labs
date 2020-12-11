package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.domain.Order;
import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.dto.OrderDto;
import com.gameshop.demo.dto.OrderGameDto;
import com.gameshop.demo.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final GameRepository gameRepository;

    @Autowired
    public OrderRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public OrderDto get(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findAny()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s could not be found", id)));
    }

    public List<OrderDto> getAll() {
        String sql = "SELECT * FROM orders";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void save(OrderDto order) {
        String sql = "INSERT INTO orders VALUES(null, ?, ?, ?)";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, order.getOrderCustomerName());
                statement.setString(2, order.getOrderAddress());
                statement.setString(3, order.getOrderDate());
                return statement;
            }
        }, holder);
        var orderId = holder.getKey();
        //jdbcTemplate.update(sql, order.getOrderCustomerName(), order.getOrderAddress(), order.getOrderDate());
        addOrderGames(orderId, order.getOrderGameList());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Long id, String address) {
        String sql = "UPDATE orders SET address = ? WHERE id = ?";
        jdbcTemplate.update(sql, address, id);
    }

    public void addOrderGames(Number orderId, List<OrderGameDto> orderGames) {
        String sql = "INSERT INTO order_game VALUES(?, ?, ?)";
        List<Object[]> argList = new ArrayList<Object[]>();
        for(OrderGameDto game: orderGames) {
            Object[] arg = {
                    orderId,
                    game.getGameId(),
                    game.getGameQuantity()
            };
            argList.add(arg);
        }
        jdbcTemplate.batchUpdate(sql, argList);
    }

    private RowMapper<OrderDto> getOrderRowMapper() {
        return (resultSet, i) -> new OrderDto(
                resultSet.getLong("id"),
                resultSet.getString("customerName"),
                resultSet.getString("address"),
                resultSet.getString("date"),
                gameRepository.getGamesForOrder(resultSet.getLong("id"))
        );
    }
}
