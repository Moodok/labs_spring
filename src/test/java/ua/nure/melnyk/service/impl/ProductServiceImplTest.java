package ua.nure.melnyk.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    private ProductServiceImpl productService = new ProductServiceImpl();

    @Mock
    private ProductDao productDao;

    private Product product = new Product("title", "desc", 10, 1L);

    @Before
    public void before() {
        productService.setProductDao(productDao);
    }

    @Test
    public void shouldGetByTitle() {
        productService.getByTitle("title");
        verify(productDao).getByTitle(eq("title"));
    }

    @Test
    public void shouldGetByUserId() {
        productService.getByUserId(1);
        verify(productDao).getByUserId(1);
    }

    @Test
    public void shouldGetById() {
        productService.getById(1);
        verify(productDao).getById(1);
    }

    @Test
    public void shouldDelete() {
        productService.delete(1);
        verify(productDao).delete(1);
    }

    @Test
    public void shouldCreate() {
        productService.create(product);
        verify(productDao).create(eq(product));
    }

    @Test
    public void shouldUpdate() {
        productService.update(product);
        verify(productDao).update(eq(product));
    }

}
