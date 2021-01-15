package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.WishlistItem;
import com.bgjshop.backend.dto.WishlistItemDto;
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
public class WishlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<WishlistItemDto> get(String userId) {
        String sql = "SELECT w.userId, w.gameId, g.title, g.imageUrl, g.price" +
                " FROM wishlist w JOIN game g on w.gameId = g.id" +
                " WHERE userId = ?";
        RowMapper<WishlistItemDto> mapper = getWishlistRowMapper();
        return jdbcTemplate.query(sql, mapper, userId);
    }

    public void add(WishlistItem wishlistItem) {
        String sql = "INSERT INTO wishlist VALUES(?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wishlistItem.getUserId());
            preparedStatement.setObject(2, wishlistItem.getGameId());
            return preparedStatement;
        });
    }

    public void removeGame(String userId, Long gameId) {
        String sql = "DELETE FROM wishlist WHERE userId = ? and gameId = ?";
        jdbcTemplate.update(sql, userId, gameId);
    }

    public void clear(String userId) {
        String sql = "DELETE FROM wishlist WHERE userId = ?";
        jdbcTemplate.update(sql, userId);
    }

    private RowMapper<WishlistItemDto> getWishlistRowMapper() {
        return (resultSet, i) -> new WishlistItemDto(
                resultSet.getString("userId"),
                resultSet.getLong("gameId"),
                resultSet.getString("title"),
                resultSet.getString("imageUrl"),
                resultSet.getFloat("price"),
                null
        );
    }
}
