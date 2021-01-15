package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.Game;
import com.bgjshop.backend.domain.Order;
import com.bgjshop.backend.domain.OrderItem;
import com.bgjshop.backend.dto.*;
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

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PopularGameDto> getPopularGames() {
        String sql = "SELECT g.id, g.title, g.imageUrl, SUM(o.quantity) AS count " +
                "FROM game g JOIN order_item o ON g.id = o.gameId " +
                "GROUP BY g.title " +
                "ORDER BY count DESC, title ASC " +
                "LIMIT 5";
        RowMapper<PopularGameDto> mapper = getPopularGameRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public OrderStatsDto getOrderStats() {
        String sql = "SELECT COUNT(*) AS total, " +
                "SUM(CASE WHEN status = 0 || status = 1 THEN 1 ELSE 0 END) AS pending, " +
                "SUM(CASE WHEN status = 3 THEN 1 ELSE 0 END) AS canceled, " +
                "SUM(CASE WHEN status = 2 THEN 1 ELSE 0 END) AS finished " +
                "FROM orders";
        RowMapper<OrderStatsDto> mapper = getOrderStatsRowMapper();
        return jdbcTemplate.query(sql, mapper).stream().findAny()
                .orElseThrow(() -> new Error());
    }

    public ReportDto getReport(String intervalType) {
        String sql = "SELECT COUNT(*) AS orders, " +
                "SUM(CASE WHEN status != 3 THEN total ELSE 0 END) AS revenue, " +
                "SUM(CASE WHEN status = 3 THEN 1 ELSE 0 END) AS canceled " +
                "FROM orders WHERE datePlaced > DATE_SUB(CURDATE(), INTERVAL "+ intervalType +")";
        RowMapper<ReportDto> mapper = getReportRowMapper();
        return jdbcTemplate.query(sql, mapper).stream().findAny()
                .orElseThrow(() -> new Error());
    }

    private RowMapper<PopularGameDto> getPopularGameRowMapper() {
        return (resultSet, i) -> new PopularGameDto(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("imageUrl"),
                resultSet.getInt("count")
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

    private RowMapper<ReportDto> getReportRowMapper() {
        return (resultSet, i) -> new ReportDto(
                resultSet.getFloat("revenue"),
                resultSet.getInt("canceled"),
                resultSet.getInt("orders")
        );
    }
}
