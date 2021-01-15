package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.Game;
import com.bgjshop.backend.dto.GameDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<GameDto> get(Long id) {
        String sql = "SELECT * FROM game WHERE id = ?";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public Optional<GameDto> getByBggId(Long bggId) {
        String sql = "SELECT * FROM game WHERE bggId = ?";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper, bggId).stream().findFirst();
    }

    public List<GameDto> getAll() {
        String sql = "SELECT * FROM game ORDER BY title";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public List<GameDto> filter(String type, Integer minPrice, Integer maxPrice, Integer minPlayers, Integer maxPlayers, Float discount) {
        String sql = "SELECT * FROM game WHERE stock > 0 " +
                (!type.equals("all") ? " && type = '" + type + "'" : " ") +
                (minPrice != 0 ? " && price" +(discount != null ? "*" + discount : " ") + " >= " + minPrice : " ") +
                (maxPrice != 0 ? " && price" +(discount != null ? "*" + discount : " ") + " <= " + maxPrice : " ") +
                (minPlayers != 0 ? " && minPlayers >= " + minPlayers : " ") +
                (maxPlayers != 0 ? " && maxPlayers <= " + maxPlayers : " ") +
                "ORDER BY title";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public List<GameDto> search(String term) {
        String searchTerm = "%" + term + "%";
        String sql = "SELECT * FROM game WHERE title LIKE ? ORDER BY title";
        RowMapper<GameDto> mapper = getGameRowMapper();
        return jdbcTemplate.query(sql, mapper, searchTerm);
    }

    public void save(Game game) {
        String sql = "INSERT INTO game VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            if(game.getBggId() != null)
                preparedStatement.setLong(2, game.getBggId());
            else
                preparedStatement.setObject(2, null);
            preparedStatement.setString(3, game.getTitle());
            preparedStatement.setString(4, game.getType());
            if(game.getImageUrl() != null)
                preparedStatement.setString(5, game.getImageUrl());
            else
                preparedStatement.setObject(5, null);
            if(game.getDescription() != null)
                preparedStatement.setString(6, game.getDescription());
            else
                preparedStatement.setObject(6, null);
            preparedStatement.setLong(7, game.getMinPlayers());
            preparedStatement.setInt(8, game.getMaxPlayers());
            preparedStatement.setInt(9, game.getPlayingTime());
            preparedStatement.setInt(10, game.getYear());
            preparedStatement.setFloat(11, game.getPrice());
            preparedStatement.setInt(12, game.getStock());
            preparedStatement.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
            return preparedStatement;
        });
    }

    public void importGames(List<Game> games) {
        String sql = "INSERT INTO game VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                     "ON DUPLICATE KEY UPDATE price= ?, stock = ?, dateModified = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;

            for (Game game : games) {
                preparedStatement.setObject(1, null);
                if(game.getBggId() != null)
                    preparedStatement.setLong(2, game.getBggId());
                else
                    preparedStatement.setObject(2, null);
                preparedStatement.setString(3, game.getTitle());
                preparedStatement.setString(4, game.getType());
                if(game.getImageUrl() != null)
                    preparedStatement.setString(5, game.getImageUrl());
                else
                    preparedStatement.setObject(5, null);
                if(game.getDescription() != null)
                    preparedStatement.setString(6, game.getDescription());
                else
                    preparedStatement.setObject(6, null);
                preparedStatement.setLong(7, game.getMinPlayers());
                preparedStatement.setInt(8, game.getMaxPlayers());
                preparedStatement.setInt(9, game.getPlayingTime());
                preparedStatement.setInt(10, game.getYear());
                preparedStatement.setFloat(11, game.getPrice());
                preparedStatement.setInt(12, game.getStock());
                preparedStatement.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setFloat(15, game.getPrice());
                preparedStatement.setInt(16, game.getStock());
                preparedStatement.setTimestamp(17, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.addBatch();
                i++;

                if (i % 1000 == 0 || i == games.size()) {
                    preparedStatement.executeBatch();
                }
            }

            return preparedStatement;
        });
    }

    public void update(Game game) {
        String sql = "UPDATE game SET bggId = ?, title = ?, type = ?, imageURL = ?, description = ?," +
                     "minPlayers = ?, maxPlayers = ?, playingTime = ?, year = ?, price = ?, stock = ?, " +
                     "dateModified = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, game.getBggId());
            preparedStatement.setString(2, game.getTitle());
            preparedStatement.setString(3, game.getType());
            preparedStatement.setString(4, game.getImageUrl());
            preparedStatement.setString(5, game.getDescription());
            preparedStatement.setLong(6, game.getMinPlayers());
            preparedStatement.setInt(7, game.getMaxPlayers());
            preparedStatement.setInt(8, game.getPlayingTime());
            preparedStatement.setInt(9, game.getYear());
            preparedStatement.setFloat(10, game.getPrice());
            preparedStatement.setInt(11, game.getStock());
            preparedStatement.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(13, game.getId());
            return preparedStatement;
        });
    }

    public void delete(Long id) {
        String sql = "DELETE FROM game WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<GameDto> getGameRowMapper() {
        return (resultSet, i) -> new GameDto(
                resultSet.getLong("id"),
                resultSet.getLong("bggId"),
                resultSet.getString("title"),
                resultSet.getString("type"),
                resultSet.getString("imageUrl"),
                resultSet.getString("description"),
                resultSet.getInt("minPlayers"),
                resultSet.getInt("maxPlayers"),
                resultSet.getInt("playingTime"),
                resultSet.getInt("year"),
                resultSet.getFloat("price"),
                null,
                resultSet.getInt("stock"),
                resultSet.getObject("dateAdded", LocalDateTime.class),
                resultSet.getObject("dateModified", LocalDateTime.class)
        );
    }
}
