package ua.nure.melnyk.integraion;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcUserDaoIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserDao jdbcUserDao;

    private User user = new User();

    @Before
    public void before() {
        user.setName("name");
        user.setEmail("email");
        user.setPhoneNumber("number");
    }

    @Test
    public void shouldInsertUser() {
        jdbcUserDao.create(user);
        assertNotNull(user.getId());
        assertNotNull(jdbcUserDao.getById(user.getId()));
    }

    @Test
    public void shouldGetSameUser() {
        jdbcUserDao.create(user);
        User userByName = jdbcUserDao.getByName("name").get(0);
        User userByEmail = jdbcUserDao.getByEmail("email");
        User userById = jdbcUserDao.getById(user.getId());
        assertEquals(user.getId(), userByName.getId());
        assertEquals(user.getId(), userByEmail.getId());
        assertEquals(user.getId(), userById.getId());
    }

    @Test
    public void shouldUpdateUser() {
        String newName = "new name";
        jdbcUserDao.create(user);
        user.setName(newName);
        jdbcUserDao.update(user);
        User userById = jdbcUserDao.getById(user.getId());
        assertEquals(newName, userById.getName());
        assertEquals(user.getPhoneNumber(), userById.getPhoneNumber());
        assertEquals(user.getEmail(), userById.getEmail());
        assertEquals(user.getId(), userById.getId());
    }

    @Test(expected = DuplicateKeyException.class)
    public void shouldNotCreateUserWithSameEmail() {
        User newUser = new User();
        newUser.setEmail("email");

        jdbcUserDao.create(user);
        jdbcUserDao.create(newUser);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldRemoveUser() {
        User newUser = new User();
        newUser.setEmail("email");

        jdbcUserDao.create(user);
        jdbcUserDao.delete(user.getId());

        jdbcUserDao.getById(user.getId());
    }
}
