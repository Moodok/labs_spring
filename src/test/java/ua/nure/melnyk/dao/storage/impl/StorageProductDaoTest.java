package ua.nure.melnyk.dao.storage.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.storage.MarketStorage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StorageProductDaoTest {

    @InjectMocks
    private StorageProductDao productDao;

    @Spy
    private MarketStorage storage;

    private Product product;

    private Product product2;

    @Before
    public void before() {
        product = new Product("title", "desc1", 20, 1L);
        product.setId(1L);
        productDao.create(product);

        product2 = new Product("title", "desc2", 20, 2L);
    }

    @Test
    public void shouldCreateProduct() {
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(storage).insertProduct(any(long.class), productCaptor.capture());

        assertProduct(productCaptor.getValue());
    }

    @Test
    public void shouldUpdateProduct() {
        productDao.update(product);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(storage).updateProduct(productCaptor.capture());

        assertProduct(productCaptor.getValue());
    }

    @Test
    public void shouldDeleteProduct() {
        productDao.delete(1);
        assertEquals(0, storage.getUsers().size());
    }

    @Test
    public void shouldGetProductById() {
        Product productById = productDao.getById(1);

        assertProduct(productById);
    }

    @Test
    public void shouldGetProductByUserId() {
        List<Product> productsByUser = productDao.getByUserId(1);

        assertProduct(productsByUser.get(0));
    }

    @Test
    public void shouldGetTwoProductsForTitle() {
        productDao.create(product2);
        List<Product> products = productDao.getByTitle("title");
        assertEquals("desc1", products.get(0).getDescription());
        assertEquals("desc2", products.get(1).getDescription());
    }


    private void assertProduct(Product product) {
        assertEquals("title", product.getTitle());
        assertEquals("desc1", product.getDescription());
        assertEquals(Integer.valueOf(20), product.getPrice());
        assertEquals(Long.valueOf(1L), product.getUserId());
        assertEquals(1, storage.getProducts().size());
    }


}
