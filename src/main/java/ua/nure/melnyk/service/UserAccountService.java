package ua.nure.melnyk.service;

import ua.nure.melnyk.entity.UserAccount;

public interface UserAccountService {

    UserAccount create(UserAccount item);

    UserAccount getById(long id);

    UserAccount update(UserAccount item);

    boolean delete(long id);

    UserAccount getByUserId(long id);

}
