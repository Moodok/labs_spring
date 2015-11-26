package ua.nure.melnyk.row.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {

    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String PRICE = "price";
    public static final String AMOUNT = "amount";

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setDescription(resultSet.getString(DESCRIPTION));
        product.setTitle(resultSet.getString(TITLE));
        product.setId(resultSet.getLong(ID));
        product.setUserId(resultSet.getLong(USER_ID));
        product.setPrice(resultSet.getInt(PRICE));
        product.setAmount(resultSet.getInt(AMOUNT));
        return product;
    }

}
