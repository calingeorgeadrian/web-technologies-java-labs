package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.Game;
import com.bgjshop.backend.domain.Order;
import com.bgjshop.backend.domain.OrderItem;
import com.bgjshop.backend.dto.OrderDto;
import com.bgjshop.backend.dto.OrderItemDto;
import com.bgjshop.backend.dto.OrderStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<OrderDto> get(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public List<OrderDto> getAll() {
        String sql = "SELECT * FROM orders";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public List<OrderDto> getAllForUser(String userId) {
        String sql = "SELECT * FROM orders WHERE userId = ?";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper, userId);
    }

    public List<OrderDto> getRecent() {
        String sql = "SELECT * FROM orders ORDER BY datePlaced DESC LIMIT 7";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public List<OrderItemDto> getGamesForOrder(Long orderId) {
        String sql = "SELECT oi.orderId, oi.gameId, g.title, oi.price, oi.quantity " +
                "FROM order_item oi JOIN game g ON oi.gameId = g.id WHERE orderId = ?";
        RowMapper<OrderItemDto> mapper = getOrderItemRowMapper();
        return jdbcTemplate.query(sql, mapper, orderId);
    }

    public List<OrderDto> getOrdersForUser(String userId) {
        String sql = "SELECT * FROM orders WHERE userId = ?";
        RowMapper<OrderDto> mapper = getOrderRowMapper();
        return jdbcTemplate.query(sql, mapper, userId);
    }

    public OrderStatsDto getStats() {
        String sql = "SELECT COUNT(*) AS total, " +
                "SUM(CASE WHEN status = 0 THEN 1 ELSE 0 END) AS pending, " +
                "SUM(CASE WHEN status = 2 THEN 1 ELSE 0 END) AS canceled, " +
                "SUM(CASE WHEN status = 3 THEN 1 ELSE 0 END) AS finished " +
                "FROM orders";
        RowMapper<OrderStatsDto> mapper = getOrderStatsRowMapper();
        return jdbcTemplate.query(sql, mapper).stream().findAny()
                .orElseThrow(() -> new Error());
    }

    public Long save(Order order) {
        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, order.getUserId());
            preparedStatement.setString(3, order.getFirstName());
            preparedStatement.setString(4, order.getLastName());
            preparedStatement.setString(5, order.getPhone());
            preparedStatement.setString(6, order.getCountry());
            preparedStatement.setString(7, order.getCity());
            preparedStatement.setString(8, order.getAddress());
            if(order.getNote() != null)
                preparedStatement.setString(9, order.getNote());
            else
                preparedStatement.setObject(9, null);
            preparedStatement.setFloat(10, order.getTotal());
            if(order.getCode() != null)
                preparedStatement.setString(11, order.getCode());
            else
                preparedStatement.setObject(11, null);
            preparedStatement.setInt(12, 0);
            preparedStatement.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(14, null);
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void saveItems(List<OrderItem> items, Long orderId) {
        String sql = "INSERT INTO order_item VALUES(?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE quantity= ?, price = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 0;

            for (OrderItem item : items) {
                preparedStatement.setLong(1, orderId);
                preparedStatement.setLong(2, item.getGameId());
                preparedStatement.setInt(3, item.getQuantity());
                preparedStatement.setFloat(4, item.getPrice());
                preparedStatement.setInt(5, item.getQuantity());
                preparedStatement.setFloat(6, item.getPrice());
                preparedStatement.addBatch();
                i++;

                if (i % 1000 == 0 || i == items.size()) {
                    preparedStatement.executeBatch();
                }
            }
            return preparedStatement;
        });
    }

    public void update(Order order) {
        String sql = "UPDATE orders SET userId = ?, firstName = ?, lastName = ?, phone = ?, country = ?," +
                "city = ?, address = ?, note = ?, total = ?, code = ?, status = ?, " +
                "dateDelivered = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getUserId());
            preparedStatement.setString(2, order.getFirstName());
            preparedStatement.setString(3, order.getLastName());
            preparedStatement.setString(4, order.getPhone());
            preparedStatement.setString(5, order.getCountry());
            preparedStatement.setString(6, order.getCity());
            preparedStatement.setString(7, order.getAddress());
            preparedStatement.setString(8, order.getNote());
            preparedStatement.setFloat(9, order.getTotal());
            preparedStatement.setString(10, order.getCode());
            preparedStatement.setInt(11, order.getStatus());
            preparedStatement.setTimestamp(12, Timestamp.valueOf(order.getDateDelivered()));
            preparedStatement.setLong(13, order.getId());
            return preparedStatement;
        });
    }

    public void updateStatus(Order order) {
        String sql = "UPDATE orders SET status = ?, dateDelivered = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getStatus());
            if(order.getStatus() == 1)
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            else if (order.getStatus() == 2)
                preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getDateDelivered()));
            else
                preparedStatement.setObject(2, null);
            preparedStatement.setLong(3, order.getId());
            return preparedStatement;
        });
    }

    public void delete(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<OrderDto> getOrderRowMapper() {
        return (resultSet, i) -> new OrderDto(
                resultSet.getLong("id"),
                resultSet.getString("userId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phone"),
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("note"),
                resultSet.getFloat("total"),
                resultSet.getString("code"),
                resultSet.getInt("status"),
                resultSet.getObject("datePlaced", LocalDateTime.class),
                resultSet.getObject("dateDelivered", LocalDateTime.class),
                null
        );
    }

    private RowMapper<OrderItemDto> getOrderItemRowMapper() {
        return (resultSet, i) -> new OrderItemDto(
                resultSet.getLong("orderId"),
                resultSet.getLong("gameId"),
                resultSet.getString("title"),
                resultSet.getFloat("price"),
                resultSet.getInt("quantity")
        );
    }

    private RowMapper<OrderStatsDto> getOrderStatsRowMapper() {
        return (resultSet, i) -> new OrderStatsDto(
                resultSet.getInt("total"),
                resultSet.getInt("pending"),
                resultSet.getInt("canceled"),
                resultSet.getInt("finished")
        );
    }
}
