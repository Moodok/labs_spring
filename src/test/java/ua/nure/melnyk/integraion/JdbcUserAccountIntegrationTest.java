package ua.nure.melnyk.integraion;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.nure.melnyk.dao.jdbc.impl.JdbcUserAccountDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.UserAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcUserAccountIntegrationTest extends BaseIntegrationTest {


    @Autowired
    private JdbcUserAccountDao jdbcUserAccountDao;

    private UserAccount userAccount;

    @Before
    public void before() {
        userAccount = new UserAccount();
        userAccount.setBalance(20);
        userAccount.setId(1L);
        userAccount.setUserId(2L);
    }

    @Test
    public void shouldInsertAccount() {
        jdbcUserAccountDao.create(userAccount);
        assertNotNull(userAccount.getId());
        assertNotNull(jdbcUserAccountDao.getById(userAccount.getId()));
    }

    @Test
    public void shouldGetSameAccount() {
        jdbcUserAccountDao.create(userAccount);
        UserAccount accountById = jdbcUserAccountDao.getById(userAccount.getId());
        UserAccount accountByUserId = jdbcUserAccountDao.getByUserId(2L);
        assertEquals(userAccount.getBalance(), accountById.getBalance());
        assertEquals(userAccount.getUserId(), accountById.getUserId());
        assertEquals(userAccount.getId(), accountById.getId());
        assertEquals(userAccount.getId(), accountByUserId.getId());
    }

    @Test
    public void shouldUpdateProduct() {
        Integer newBalance = 10;
        jdbcUserAccountDao.create(userAccount);
        userAccount.setBalance(newBalance);
        jdbcUserAccountDao.update(userAccount);
        UserAccount accountById = jdbcUserAccountDao.getById(userAccount.getId());
        assertEquals(newBalance, accountById.getBalance());
        assertEquals(userAccount.getBalance(), accountById.getBalance());
        assertEquals(userAccount.getUserId(), accountById.getUserId());
        assertEquals(userAccount.getId(), accountById.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldDeleteProduct() {
        jdbcUserAccountDao.create(userAccount);
        jdbcUserAccountDao.delete(userAccount.getId());
        jdbcUserAccountDao.getById(userAccount.getId());
    }

}
