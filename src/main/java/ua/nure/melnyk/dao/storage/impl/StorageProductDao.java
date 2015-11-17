package ua.nure.melnyk.dao.storage.impl;

import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.AbstractDao;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StorageProductDao extends AbstractDao implements ProductDao {

    @Override
    public List<Product> getByUserId(long id) {
        return getStorage().getProducts().stream()
                .filter(p -> p.getUserId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getByTitle(String title) {
        return getStorage().getProducts().stream()
                .filter(p -> p.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public Product create(Product item) {
        item.setId(generateId());
        getStorage().insertProduct(item.getId(), item);
        return item;
    }

    @Override
    public Product getById(long id) {
        return getStorage().getProductById(id);
    }

    @Override
    public Product update(Product item) {
        getStorage().updateProduct(item);
        return item;
    }

    @Override
    public boolean delete(long id) {
        return getStorage().deleteProductById(id);
    }

}
