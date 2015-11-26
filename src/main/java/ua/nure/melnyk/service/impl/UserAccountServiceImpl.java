package ua.nure.melnyk.service.impl;

import ua.nure.melnyk.dao.UserAccountDao;
import ua.nure.melnyk.entity.UserAccount;
import ua.nure.melnyk.service.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDao userAccountDao;

    @Override
    public UserAccount create(UserAccount item) {
        return userAccountDao.create(item);
    }

    @Override
    public UserAccount getById(long id) {
        return userAccountDao.getById(id);
    }

    @Override
    public UserAccount update(UserAccount item) {
        return userAccountDao.update(item);
    }

    @Override
    public boolean delete(long id) {
        return userAccountDao.delete(id);
    }

    @Override
    public UserAccount getByUserId(long id) {
        return userAccountDao.getByUserId(id);
    }

    public void setUserAccountDao(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }
}
