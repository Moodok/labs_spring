package ua.nure.melnyk.service.impl;

import org.springframework.beans.factory.annotation.Required;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Override
    public List<Product> getByUserId(long id) {
        return productDao.getByUserId(id);
    }

    @Override
    public List<Product> getByTitle(String title) {
        return productDao.getByTitle(title);
    }

    @Override
    public Product create(Product item) {
        return productDao.create(item);
    }

    @Override
    public Product getById(long id) {
        return productDao.getById(id);
    }

    @Override
    public Product update(Product item) {
        return productDao.update(item);
    }

    @Override
    public boolean delete(long id) {
        return productDao.delete(id);
    }

    @Required
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
