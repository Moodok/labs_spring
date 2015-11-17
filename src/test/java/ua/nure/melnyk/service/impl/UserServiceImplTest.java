package ua.nure.melnyk.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.melnyk.dao.UserDao;
import ua.nure.melnyk.entity.User;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private UserDao userDao;

    private User user = new User("name", "email", "number");

    @Before
    public void before() {
        userService.setUserDao(userDao);
    }

    @Test
    public void shouldGetByEmail() {
        userService.getByEmail("email");
        verify(userDao).getByEmail(eq("email"));
    }

    @Test
    public void shouldGetByName() {
        userService.getByName("name");
        verify(userDao).getByName(eq("name"));
    }

    @Test
    public void shouldGetById() {
        userService.getById(1);
        verify(userDao).getById(1);
    }

    @Test
    public void shouldDelete() {
        userService.delete(1);
        verify(userDao).delete(1);
    }

    @Test
    public void shouldCreate() {
        userService.create(user);
        verify(userDao).create(eq(user));
    }

    @Test
    public void shouldUpdate() {
        userService.update(user);
        verify(userDao).update(eq(user));
    }
}
