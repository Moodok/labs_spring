package ua.nure.melnyk.integraion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
@Rollback(false)
@Transactional
public class JdbcIntegrationTest {

    @Autowired
    private ProductDao jdbcProductDao;

    private Product product = new Product();

    @Before
    public void before() {
        product.setTitle("title");
        product.setPrice(10);
        product.setDescription("description");
    }


    @Test
    public void shouldInsertProduct() {
        jdbcProductDao.create(product);
        assertNotNull(product.getId());
    }


}
