package ua.nure.melnyk.dao;

public interface Dao<T> {

    T create(T item);

    T getById(long id);

    T update();

    boolean delete(long id);


}
