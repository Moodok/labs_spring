package ua.nure.melnyk.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-test-config.xml"}),
        @ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
})
@Rollback
@Transactional
@ActiveProfiles("test")
public class HomeControllerIntegrationTest {

    public static final String STUB = "stub";
    public static final String ROLE_USER = "ROLE_USER";
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private User user = new User();

    @Autowired
    private MarketFacade marketFacade;

    private Authentication principal = new UsernamePasswordAuthenticationToken(user, STUB,
            Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));

    @Before
    public void before() {
        mockMvc = webAppContextSetup(wac).build();

    }

    @Test
    public void shouldLoadHome() throws Exception {
        mockMvc.perform(get("/")
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/home.jsp"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void shouldLoadLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/login.jsp"));
    }

    @Test
    public void shouldLoadInfoAfterProductNotFound() throws Exception {
        mockMvc.perform(post("/info/100"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/info.jsp"))
                .andExpect(model().attribute("product", new Product()));
    }

    @Test
    public void shouldLoadInfo() throws Exception {
        Product product = new Product();
        marketFacade.createProduct(product);

        mockMvc.perform(post("/info/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("info"))
                .andExpect(model().attribute("product", product));
    }

}
