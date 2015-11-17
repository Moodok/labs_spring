package ua.nure.melnyk.service.impl;

import org.springframework.beans.factory.annotation.Required;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User create(User item) {
        return userDao.create(item);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User update(User item) {
        return userDao.update(item);
    }

    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
