package ua.nure.melnyk.facade.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.entity.UserAccount;
import ua.nure.melnyk.exceptions.LowAmountException;
import ua.nure.melnyk.exceptions.LowBalanceException;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.service.ProductService;
import ua.nure.melnyk.service.UserAccountService;
import ua.nure.melnyk.service.UserService;

import java.util.List;

public class MarketFacadeImpl implements MarketFacade {

    private ProductService productService;

    private UserService userService;

    private UserAccountService userAccountService;

    public MarketFacadeImpl(ProductService productService, UserService userService, UserAccountService userAccountService) {
        this.productService = productService;
        this.userService = userService;
        this.userAccountService = userAccountService;
    }

    @Override
    public User createUser(User user) {
        user = userService.create(user);
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(user.getId());
        userAccountService.create(userAccount);
        return user;
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

    @Override
    public void updateBalance(Integer change, Long userId) {
        UserAccount accountByUserId = userAccountService.getByUserId(userId);
        if (change < 0 && accountByUserId.getBalance() < Math.abs(change))
            throw new LowBalanceException();
        accountByUserId.setBalance(accountByUserId.getBalance() + change);
        userAccountService.update(accountByUserId);
    }

    @Override
    @Transactional
    public void buyProduct(Long productId, Long buyerId) {
        Product product = productService.getById(productId);
        if (product.getAmount() <= 0)
            throw new LowAmountException();
        updateBalance(-product.getPrice(), buyerId);
        updateBalance(product.getPrice(), product.getUserId());
        product.setAmount(product.getAmount() - 1);
        productService.update(product);
    }

    @Override
    public UserAccount getUserAccountByUserId(long id) {
        return userAccountService.getByUserId(id);
    }


}
