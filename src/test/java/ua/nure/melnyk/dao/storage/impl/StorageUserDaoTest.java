package ua.nure.melnyk.dao.storage.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.storage.MarketStorage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StorageUserDaoTest {

    @InjectMocks
    private StorageUserDao userDao;

    @Spy
    private MarketStorage storage;

    private User user;

    private User user2;

    @Before
    public void before() {
        user = new User("name", "email", "number");
        user.setId(1);
        userDao.create(user);

        user2 = new User("name", "email2", "number2");
    }

    @Test
    public void shouldCreateUser() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(storage).insertUser(any(long.class), userCaptor.capture());

        assertUser(userCaptor.getValue());
    }

    @Test
    public void shouldUpdateUser() {
        userDao.update(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(storage).updateUser(userCaptor.capture());

        assertUser(userCaptor.getValue());
    }

    @Test
    public void shouldDeleteUser() {
        userDao.delete(1);
        assertEquals(0, storage.getUsers().size());
    }

    @Test
    public void shouldGetUserById() {
        User userById = userDao.getById(1);

        assertUser(userById);
    }

    @Test
    public void shouldGetUserByEmail() {
        User userByEmail = userDao.getByEmail("email");

        assertEquals("email", userByEmail.getEmail());
        assertEquals("name", userByEmail.getName());
        assertEquals("number", userByEmail.getPhoneNumber());
    }

    @Test
    public void shouldGetNothingForUserWithNotExistingEmail() {
        User userByEmail = userDao.getByEmail("cool");
        assertNull(userByEmail);
    }

    @Test
    public void shouldGetTwoUsersForName() {
        userDao.create(user2);
        List<User> users = userDao.getByName("name");
        assertEquals("email", users.get(0).getEmail());
        assertEquals("email2", users.get(1).getEmail());
    }

    private void assertUser(User user) {
        assertEquals("email", user.getEmail());
        assertEquals("name", user.getName());
        assertEquals("number", user.getPhoneNumber());
        assertEquals(1, storage.getUsers().size());
    }
}
