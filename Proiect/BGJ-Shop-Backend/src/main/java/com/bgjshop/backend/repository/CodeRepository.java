package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.Code;
import com.bgjshop.backend.dto.CodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CodeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<CodeDto> get(String code) {
        String sql = "SELECT * FROM code WHERE code = ?";
        RowMapper<CodeDto> mapper = getCodeRowMapper();
        return jdbcTemplate.query(sql, mapper, code).stream().findFirst();
    }

    public List<CodeDto> getAll() {
        String sql = "SELECT * FROM code";
        RowMapper<CodeDto> mapper = getCodeRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void addCode(Code code) {
        String sql = "INSERT INTO code VALUES(?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, code.getCode());
            preparedStatement.setInt(2, code.getType());
            preparedStatement.setInt(3, code.getDiscount());
            return preparedStatement;
        });
    }

    public void deleteCode(String code) {
        String sql = "DELETE FROM code WHERE code = ?";
        jdbcTemplate.update(sql, code);
    }

    private RowMapper<CodeDto> getCodeRowMapper() {
        return (resultSet, i) -> new CodeDto(
                resultSet.getString("code"),
                resultSet.getInt("type"),
                resultSet.getInt("discount")
        );
    }
}
