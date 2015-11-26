package ua.nure.melnyk.dao;

import ua.nure.melnyk.entity.UserAccount;

public interface UserAccountDao extends Dao<UserAccount> {

    UserAccount getByUserId(long id);

}
