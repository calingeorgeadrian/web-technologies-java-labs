package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.CartItem;
import com.bgjshop.backend.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CartItemDto> get(String userId) {
        String sql = "SELECT c.userId, c.gameId, g.title, g.imageUrl, g.price, c.quantity" +
                " FROM cart c JOIN game g on c.gameId = g.id" +
                " WHERE userId = ?";
        RowMapper<CartItemDto> mapper = getCartRowMapper();
        return jdbcTemplate.query(sql, mapper, userId);
    }

    public void add(CartItem cartItem) {
        String sql = "INSERT INTO cart VALUES(?, ?, ?)" +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, cartItem.getUserId());
            preparedStatement.setObject(2, cartItem.getGameId());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.setInt(4, cartItem.getQuantity());
            return preparedStatement;
        });
    }

    public void removeGame(String userId, Long gameId) {
        String sql = "DELETE FROM cart WHERE userId = ? and gameId = ?";
        jdbcTemplate.update(sql, userId, gameId);
    }

    public void updateItem(CartItem cartItem) {
        String sql = "UPDATE cart SET quantity = ? WHERE userId = ? and gameId = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, cartItem.getQuantity());
                preparedStatement.setString(2, cartItem.getUserId());
                preparedStatement.setLong(3, cartItem.getGameId());
            return preparedStatement;
        });
    }

    public void empty(String userId) {
        String sql = "DELETE FROM cart WHERE userId = ?";
        jdbcTemplate.update(sql, userId);
    }

    private RowMapper<CartItemDto> getCartRowMapper() {
        return (resultSet, i) -> new CartItemDto(
                resultSet.getString("userId"),
                resultSet.getLong("gameId"),
                resultSet.getString("title"),
                resultSet.getString("imageUrl"),
                resultSet.getFloat("price"),
                null,
                resultSet.getInt("quantity")
        );
    }
}
