package ua.nure.melnyk.dao.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.UserAccountDao;
import ua.nure.melnyk.entity.UserAccount;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class JdbcUserAccountDao implements UserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<UserAccount> userAccountRowMapper;

    @Override
    public UserAccount create(UserAccount item) {
        String sql = "INSERT INTO accounts (balance, userId) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setObject(k++, item.getBalance());
            ps.setObject(k++, item.getUserId());
            return ps;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public UserAccount getById(long id) {
        String query = "SELECT * FROM accounts WHERE id = ?";
        return jdbcTemplate.queryForObject(query, userAccountRowMapper, id);
    }

    @Override
    public UserAccount update(UserAccount item) {
        String sql = "UPDATE accounts SET balance=? WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{item.getBalance(), item.getId()});
        return item;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        int rowCount = jdbcTemplate.update(sql, new Object[]{id});
        return rowCount > 0;
    }

    @Override
    public UserAccount getByUserId(long id) {
        String query = "SELECT * FROM accounts WHERE userId = ?";
        return jdbcTemplate.queryForObject(query, userAccountRowMapper, id);
    }

}
