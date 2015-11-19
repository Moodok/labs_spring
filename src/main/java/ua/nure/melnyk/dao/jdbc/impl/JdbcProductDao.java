package ua.nure.melnyk.dao.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Product> productRowMapper;

    @Override
    public List<Product> getByUserId(long id) {
        String query = "SELECT * FROM products WHERE userID = ?";
        return jdbcTemplate.query(query, productRowMapper, id);
    }

    @Override
    public List<Product> getByTitle(String title) {
        String query = "SELECT * FROM products WHERE title = ?";
        return jdbcTemplate.query(query, productRowMapper, title);
    }

    @Override
    public Product create(Product item) {
        String sql = "INSERT INTO products (title, description, price, userId) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            ps.setObject(k++, item.getTitle());
            ps.setObject(k++, item.getDescription());
            ps.setObject(k++, item.getPrice());
            ps.setObject(k, item.getUserId());
            return ps;
        }, keyHolder);
        item.setId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public Product getById(long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, productRowMapper, id);
    }

    @Override
    public Product update(Product item) {
        String sql = "UPDATE products SET title=?, description=?, price=?, userId=? WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{item.getTitle(), item.getDescription(), item.getPrice(), item.getUserId(), item.getId()});
        return item;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int rowCount = jdbcTemplate.update(sql, new Object[]{id});
        return rowCount > 0;
    }

}
