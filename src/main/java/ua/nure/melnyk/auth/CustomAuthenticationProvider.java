package ua.nure.melnyk.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;

import java.util.Arrays;
import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MarketFacade marketFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        try {
            User user = marketFacade.getUserByEmail(name);
            return new UsernamePasswordAuthenticationToken(user, "stub",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
