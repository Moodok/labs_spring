package ua.nure.melnyk.dao;

import ua.nure.melnyk.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    User getByEmail(String email);

    List<User> getByName(String name);

}
