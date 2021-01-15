package com.bgjshop.backend.repository;

import com.bgjshop.backend.domain.User;
import com.bgjshop.backend.dto.UserDto;
import com.bgjshop.backend.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<UserDto> get(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<UserDto> mapper = getUserRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public void register(User user) {
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getCountry());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setInt(9, user.getRoleType());
            preparedStatement.setObject(10, user.getPasswordHash());
            preparedStatement.setObject(11, user.getPasswordSalt());
            return preparedStatement;
        });
    }

    public Optional<UserDto> login(UserLoginDto userLogin) {
        String sql = "SELECT * FROM user WHERE email = ?";
        RowMapper<UserDto> mapper = getUserRowMapper();
        return jdbcTemplate.query(sql, mapper, userLogin.getEmail()).stream().findFirst();
    }

    public void update(User user) {
        String sql = "UPDATE user SET email = ?, firstName = ?, lastName = ?, " +
                "phone = ?, country = ?, city = ?, address = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getCountry());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setObject(8, user.getId().toString());
            return preparedStatement;
        });
    }

    public void delete(UUID id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<UserDto> getUserRowMapper() {
        return (resultSet, i) -> new UserDto(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("phone"),
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getInt("roleType"),
                resultSet.getBytes("passwordSalt"),
                resultSet.getBytes("passwordHash")
        );
    }
}
