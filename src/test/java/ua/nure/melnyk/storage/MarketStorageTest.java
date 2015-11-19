package ua.nure.melnyk.storage;

import org.junit.Before;
import org.junit.Test;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarketStorageTest {

    private MarketStorage storage = new MarketStorage();

    Product product1;

    User user1;

    @Before
    public void before() {
        product1 = new Product("product1", "desc1", 20, 1L);
        storage.insertProduct(1, product1);
        storage.insertProduct(2, new Product("product2", "desc2", 30, 2L));

        user1 = new User("name1", "email1", "number1");
        storage.insertUser(1, user1);
        storage.insertUser(2, new User("name2", "email2", "number2"));
    }

    @Test
    public void shouldGetProduct() {
        Product productById = storage.getProductById(1);
        assertEquals("product1", productById.getTitle());
        assertEquals("desc1", productById.getDescription());
        assertEquals(Integer.valueOf(20), productById.getPrice());
        assertEquals(Long.valueOf(1L), productById.getUserId());
    }

    @Test
    public void shouldGetAllProducts() {
        assertEquals(2, storage.getProducts().size());
    }

    @Test
    public void shouldGetUser() {
        User userById = storage.getUserById(1);
        assertEquals("email1", userById.getEmail());
        assertEquals("name1", userById.getName());
        assertEquals("number1", userById.getPhoneNumber());
    }

    @Test
    public void shouldGetAllUsers() {
        assertEquals(2, storage.getUsers().size());
    }

    @Test
    public void shouldDeleteOneUser() {
        assertTrue(storage.deleteUserById(1));
        assertEquals(1, storage.getUsers().size());
    }

    @Test
    public void shouldDeleteOneProduct() {
        assertTrue(storage.deleteProductById(1));
        assertEquals(1, storage.getProducts().size());
    }

    @Test
    public void shouldUpdateUser() {
        user1.setEmail("newEmail");
        storage.updateUser(user1);
        User userById = storage.getUserById(1);
        assertEquals("newEmail", userById.getEmail());
    }

    @Test
    public void souldUpdateProduct() {
        product1.setTitle("newTitle");
        storage.updateProduct(product1);
        Product productById = storage.getProductById(1);
        assertEquals("newTitle", productById.getTitle());
    }

}
