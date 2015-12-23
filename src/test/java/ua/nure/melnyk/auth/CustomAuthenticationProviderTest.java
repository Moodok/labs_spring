package ua.nure.melnyk.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomAuthenticationProviderTest {

    public static final String NAME = "name";
    @InjectMocks
    private CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();

    @Mock
    private MarketFacade marketFacade;

    @Mock
    private Authentication authentication;

    @Before
    public void before() {
        when(authentication.getName()).thenReturn(NAME);
    }

    @Test
    public void shouldSupportLoginPassword() {
        assertTrue(customAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void shouldAuthenticate() {
        User user = new User();
        when(marketFacade.getUserByEmail(NAME)).thenReturn(user);
        Authentication auth = customAuthenticationProvider.authenticate(authentication);
        assertEquals(UsernamePasswordAuthenticationToken.class, auth.getClass());
        assertEquals(user, auth.getPrincipal());
    }

}