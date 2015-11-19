package ua.nure.melnyk.dao.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<User> userRowMapper;

    @Override
    public User getByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(query, userRowMapper, email);
    }

    @Override
    public List<User> getByName(String name) {
        String query = "SELECT * FROM users WHERE name = ?";
        return jdbcTemplate.query(query, userRowMapper, name);
    }

    @Override
    public User create(User item) {
        String sql = "INSERT INTO users (name, email, phoneNumber) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setObject(k++, item.getName());
            ps.setObject(k++, item.getEmail());
            ps.setObject(k++, item.getPhoneNumber());
            return ps;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public User getById(long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(query, userRowMapper, id);
    }

    @Override
    public User update(User item) {
        String sql = "UPDATE users SET name=?, email=?, phoneNumber=? WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{item.getName(), item.getEmail(), item.getPhoneNumber(), item.getId()});
        return item;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowCount = jdbcTemplate.update(sql, new Object[]{id});
        return rowCount > 0;
    }

}
