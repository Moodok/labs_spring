package ua.nure.melnyk.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.Unmarshaller;
import org.springframework.transaction.support.TransactionTemplate;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.Products;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbProductLoader {

    private Logger logger = LoggerFactory.getLogger(DbProductLoader.class);

    @Value("${products.xml}")
    private String productsXmlPath;

    @Resource(name = "castorMarshaller")
    private Unmarshaller unmarshaller;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void loadProducts() {
        try (InputStream inputStream = DbProductLoader.class.getClassLoader().getResourceAsStream(productsXmlPath)) {
            Products products = (Products) unmarshaller.unmarshal(new StreamSource(inputStream));
            List<Product> productList = products.getProducts();
            String sql = "INSERT INTO products (title, description, price, userId) VALUES (?, ?, ?, ?)";
            transactionTemplate.execute(transactionStatus -> {
                productsBatchInsert(products, productList, sql);
                return null;
            });
        } catch (IOException | DuplicateKeyException e) {
            logger.info(e.getMessage());
        }
    }

    private void productsBatchInsert(final Products products, final List<Product> productList, String sql) {
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Product product = productList.get(i);
                int k = 1;
                preparedStatement.setObject(k++, product.getTitle());
                preparedStatement.setObject(k++, product.getDescription());
                preparedStatement.setObject(k++, product.getPrice());
                preparedStatement.setObject(k, product.getUserId());
            }

            @Override
            public int getBatchSize() {
                return products.getProducts().size();
            }
        });
    }

}
