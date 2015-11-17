package ua.nure.melnyk.dao;

import ua.nure.melnyk.entity.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {

    List<Product> getByUserId(long id);

    List<Product> getByTitle(String title);

}
