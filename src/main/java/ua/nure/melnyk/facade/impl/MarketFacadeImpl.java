package ua.nure.melnyk.facade.impl;

import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.service.ProductService;
import ua.nure.melnyk.service.UserService;

import java.util.List;

public class MarketFacadeImpl implements MarketFacade {

    private ProductService productService;

    private UserService userService;

    public MarketFacadeImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public User createUser(User user) {
        return userService.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.update(user);
    }

    @Override
    public User getUserById(long id) {
        return userService.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userService.getByName(name);
    }

    @Override
    public boolean deleteUser(long id) {
        return userService.delete(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productService.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productService.update(product);
    }

    @Override
    public Product getProductById(long id) {
        return productService.getById(id);
    }

    @Override
    public List<Product> getProductsByTitle(String title) {
        return productService.getByTitle(title);
    }

    @Override
    public List<Product> getProductsByUserId(long id) {
        return productService.getByUserId(id);
    }

    @Override
    public boolean deleteProduct(long id) {
        return productService.delete(id);
    }
}
