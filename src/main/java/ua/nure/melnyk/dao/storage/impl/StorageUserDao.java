package ua.nure.melnyk.dao.storage.impl;

import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.AbstractDao;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StorageUserDao extends AbstractDao implements UserDao {

    @Override
    public User getByEmail(String email) {
        return getStorage().getUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseGet(() -> null);
    }

    @Override
    public List<User> getByName(String name) {
        return getStorage().getUsers().stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public User create(User item) {
        item.setId(generateId());
        getStorage().insertUser(item.getId(), item);
        return item;
    }

    @Override
    public User getById(long id) {
        return getStorage().getUserById(id);
    }

    @Override
    public User update(User item) {
        getStorage().updateUser(item);
        return item;
    }

    @Override
    public boolean delete(long id) {
        return getStorage().deleteUserById(id);
    }

}
