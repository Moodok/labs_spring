package ua.nure.melnyk.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.Products;
import ua.nure.melnyk.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class MarketStorage {

    private Logger logger = LoggerFactory.getLogger(MarketStorage.class);

    private static final String PRODUCT = "Product:";

    private static final String USER = "User:";

    private AtomicLong atomicLong = new AtomicLong();

    private Map<String, Object> map = new HashMap<>();

    @Value("${products.xml}")
    private String productsXmlPath;

    @Resource(name = "castorMarshaller")
    private Unmarshaller unmarshaller;

    @PostConstruct
    public void postConstruct() {
        try (InputStream inputStream = MarketStorage.class.getResourceAsStream(productsXmlPath)) {
            Products products = (Products) unmarshaller.unmarshal(new StreamSource(inputStream));
            for (Product product : products.getProducts()) {
                product.setId(atomicLong.incrementAndGet());
                insertProduct(product.getId(), product);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public AtomicLong getAtomicLong() {
        return atomicLong;
    }

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

    public void setProductsXmlPath(String productsXmlPath) {
        this.productsXmlPath = productsXmlPath;
    }

}
