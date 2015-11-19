package ua.nure.melnyk.integraion;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcProductDaoIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductDao jdbcProductDao;

    private Product product = new Product();

    @Before
    public void before() {
        product.setTitle("title");
        product.setPrice(10);
        product.setDescription("description");
        product.setUserId(1L);
    }

    @Test
    public void shouldInsertProduct() {
        jdbcProductDao.create(product);
        assertNotNull(product.getId());
        assertNotNull(jdbcProductDao.getById(product.getId()));
    }

    @Test
    public void shouldGetSameProduct() {
        jdbcProductDao.create(product);
        Product productByTitle = jdbcProductDao.getByTitle("title").get(0);
        Product productByUserId = jdbcProductDao.getByUserId(1L).get(0);
        Product productById = jdbcProductDao.getById(product.getId());
        assertEquals(product.getId(), productById.getId());
        assertEquals(product.getId(), productByTitle.getId());
        assertEquals(product.getId(), productByUserId.getId());
    }

    @Test
    public void shouldUpdateProduct() {
        String newTitle = "new title";
        jdbcProductDao.create(product);
        product.setTitle(newTitle);
        jdbcProductDao.update(product);
        Product productById = jdbcProductDao.getById(product.getId());
        assertEquals(newTitle, productById.getTitle());
        assertEquals(product.getDescription(), productById.getDescription());
        assertEquals(product.getPrice(), productById.getPrice());
        assertEquals(product.getUserId(), productById.getUserId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteProduct() {
        jdbcProductDao.create(product);
        jdbcProductDao.delete(product.getId());
        jdbcProductDao.getById(product.getId());
    }

}
