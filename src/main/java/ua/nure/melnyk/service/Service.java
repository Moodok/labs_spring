package ua.nure.melnyk.service;

public interface Service<T> {

    T create(T item);

    T getById(long id);

    T update(T item);

    boolean delete(long id);

}
