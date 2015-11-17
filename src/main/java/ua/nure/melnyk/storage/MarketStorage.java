package ua.nure.melnyk.storage;

import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MarketStorage {

    private static final String PRODUCT = "Product:";

    private static final String USER = "User:";

    private Map<String, Object> map = new HashMap<>();

    public Product getProductById(long id) {
        return (Product) map.get(PRODUCT + id);
    }

    public User getUserById(long id) {
        return (User) map.get(USER + id);
    }

    public List<Product> getProducts() {
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().contains(PRODUCT))
                .map(e -> (Product) e.getValue())
                .collect(Collectors.toList());
    }

    public List<User> getUsers() {
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().contains(USER))
                .map(e -> (User) e.getValue())
                .collect(Collectors.toList());
    }

    public void insertUser(long id, User user) {
        map.put(USER + id, user);
    }

    public void insertProduct(long id, Product product) {
        map.put(PRODUCT + id, product);
    }

    public boolean deleteUserById(long id) {
        return map.remove(USER + id) != null;
    }

    public boolean deleteProductById(long id) {
        return map.remove(PRODUCT + id) != null;
    }

    public void updateUser(User user) {
        map.put(USER + user.getId(), user);
    }

    public void updateProduct(Product product) {
        map.put(PRODUCT + product.getId(), product);
    }

}
