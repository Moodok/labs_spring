package ua.nure.melnyk.integraion;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.storage.DbProductLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoadProductsIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ProductDao jdbcProductDao;

    @Autowired
    private DbProductLoader dbProductLoader;

    @Test
    public void shouldLoadNothing() {
        List<Product> productsByUserId = jdbcProductDao.getByUserId(1L);
        assertEquals(0, productsByUserId.size());
    }

    @Test
    public void shouldLoadProducts() {
        dbProductLoader.loadProducts();
        List<Product> productsByUserId = jdbcProductDao.getByUserId(1L);
        assertEquals(2, productsByUserId.size());

    }

}
