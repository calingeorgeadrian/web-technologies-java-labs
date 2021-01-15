package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.Promotion;
import com.bgjshop.backend.dto.PromotionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class PromotionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<PromotionDto> get(Long id) {
        String sql = "SELECT * FROM promotion WHERE id = ?";
        RowMapper<PromotionDto> mapper = getPromotionRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public PromotionDto getActive() {
        String sql = "SELECT p.id, p.title, max(discount) as discount, p.startDate, p.endDate from promotion p " +
                "where now() >= p.startDate and now() <= p.endDate && p.discount = discount";
        RowMapper<PromotionDto> mapper = getPromotionRowMapper();
        return jdbcTemplate.query(sql, mapper).stream().findAny()
                .orElseThrow(() -> new Error());
    }

    public List<PromotionDto> getAll() {
        String sql = "SELECT * FROM promotion";
        RowMapper<PromotionDto> mapper = getPromotionRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void save(Promotion promotion) {
        String sql = "INSERT INTO promotion VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, promotion.getTitle());
            preparedStatement.setInt(3, promotion.getDiscount());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(promotion.getStartDate()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(promotion.getEndDate()));
            return preparedStatement;
        });
    }

    public void update(Promotion promotion) {
        String sql = "UPDATE promotion SET title = ?, discount = ?, startDate = ?, endDate = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, promotion.getTitle());
            preparedStatement.setInt(2, promotion.getDiscount());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(promotion.getStartDate()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(promotion.getEndDate()));
            preparedStatement.setLong(5, promotion.getId());
            return preparedStatement;
        });
    }

    public void delete(Long id) {
        String sql = "DELETE FROM promotion WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<PromotionDto> getPromotionRowMapper() {
        return (resultSet, i) -> new PromotionDto(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getInt("discount"),
                resultSet.getObject("startDate", LocalDateTime.class),
                resultSet.getObject("endDate", LocalDateTime.class)
        );
    }
}
