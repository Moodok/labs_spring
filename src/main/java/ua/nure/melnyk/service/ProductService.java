package ua.nure.melnyk.service;

import ua.nure.melnyk.entity.Product;

import java.util.List;

public interface ProductService extends Service<Product>{

    List<Product> getByUserId(long id);

    List<Product> getByTitle(String title);

}
