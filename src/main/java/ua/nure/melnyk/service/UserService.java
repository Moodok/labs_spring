package ua.nure.melnyk.service;

import ua.nure.melnyk.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

    User getByEmail(String email);

    List<User> getByName(String name);

}
