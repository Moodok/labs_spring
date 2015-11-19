package ua.nure.melnyk.facade.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.service.ProductService;
import ua.nure.melnyk.service.UserService;
import ua.nure.melnyk.service.impl.UserServiceImpl;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MarketFacadeImplTest {

    private MarketFacade marketFacade;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    private Product product;

    private User user;

    @Before
    public void before() {
        setProduct();
        setUser();

        marketFacade = new MarketFacadeImpl(productService, userService);
    }

    @Test
    public void shouldGetProductByTitle() {
        marketFacade.getProductsByTitle("title");
        verify(productService).getByTitle(eq("title"));
    }

    @Test
    public void shouldGetProductByUserId() {
        marketFacade.getProductsByUserId(1);
        verify(productService).getByUserId(1);
    }

    @Test
    public void shouldGetProductById() {
        marketFacade.getProductById(1);
        verify(productService).getById(1);
    }

    @Test
    public void shouldDeleteProduct() {
        marketFacade.deleteProduct(1);
        verify(productService).delete(1);
    }

    @Test
    public void shouldCreateProduct() {
        marketFacade.createProduct(product);
        verify(productService).create(eq(product));
    }

    @Test
    public void shouldUpdateProduct() {
        marketFacade.updateProduct(product);
        verify(productService).update(eq(product));
    }

    @Test
    public void shouldGetUserByEmail() {
        marketFacade.getUserByEmail("email");
        verify(userService).getByEmail(eq("email"));
    }

    @Test
    public void shouldGetUserByName() {
        marketFacade.getUsersByName("name");
        verify(userService).getByName(eq("name"));
    }

    @Test
    public void shouldGetUserById() {
        marketFacade.getUserById(1);
        verify(userService).getById(1);
    }

    @Test
    public void shouldDeleteUser() {
        marketFacade.deleteUser(1);
        verify(userService).delete(1);
    }

    @Test
    public void shouldCreateUser() {
        marketFacade.createUser(user);
        verify(userService).create(eq(user));
    }

    @Test
    public void shouldUpdateUser() {
        marketFacade.updateUser(user);
        verify(userService).update(eq(user));
    }

    private void setUser() {
        user = new User();
        user.setEmail("email");
        user.setName("name");
        user.setPhoneNumber("number");
    }

    private void setProduct() {
        product = new Product();
        product.setTitle("title");
        product.setDescription("desc");
        product.setPrice(10);
        product.setUserId(1L);
    }

}
