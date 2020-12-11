package com.gameshop.demo.repository;

import com.gameshop.demo.domain.Game;
import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.dto.OrderGameDto;
import com.gameshop.demo.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GameDto get(String id) {
        String sql = "SELECT * FROM game WHERE id = ?";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findAny()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Game with id %s could not be found", id)));
    }

    public List<GameDto> getAll() {
        String sql = "SELECT * FROM game";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void save(Game game) {
        String sql = "INSERT INTO game VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, game.getId(), game.getTitle(), game.getDescription(), game.getPrice(), game.getStock());
    }

    public void delete(String id) {
        String sql = "DELETE FROM game WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Long id, String title) {
        String sql = "UPDATE game SET title = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, id);
    }

    public List<OrderGameDto> getGamesForOrder(Long orderId) {
        String sql = "SELECT og.orderId, og.gameId, g.title, g.stock, g.price, og.quantity " +
                "FROM order_game as og join orders as o on og.orderId = o.id " +
                "join game as g on og.gameId = g.id " +
                "WHERE og.orderId = ?";
        RowMapper<OrderGameDto> mapper = getOrderGameRowMapper();
        return jdbcTemplate.query(sql, mapper, orderId);
    }

    private RowMapper<GameDto> getGameRowMapper() {
        return (resultSet, i) -> new GameDto(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getInt("price"),
                resultSet.getInt("stock")
        );
    }

    private RowMapper<OrderGameDto> getOrderGameRowMapper() {
        return (resultSet, i) -> new OrderGameDto(
                resultSet.getLong("orderId"),
                resultSet.getLong("gameId"),
                resultSet.getString("title"),
                resultSet.getInt("stock"),
                resultSet.getInt("price"),
                resultSet.getInt("quantity")
        );
    }
}
