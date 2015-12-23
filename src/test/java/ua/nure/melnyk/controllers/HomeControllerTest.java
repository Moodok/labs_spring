package ua.nure.melnyk.controllers;

import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.facade.MarketFacade;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final String DESC = "desc";
    @InjectMocks
    private HomeController homeController = new HomeController();

    @Mock
    private MarketFacade marketFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(homeController).build();
    }

    @Test
    public void shouldNotAddProduct() throws Exception {
        mockMvc.perform(post("/add-product"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldAddProcut() throws Exception {
        mockMvc.perform(post("/add-product")
                .param(TITLE, TITLE)
                .param(DESCRIPTION, DESC)
                .param("price", "10"))
                .andExpect(status().isFound());
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(marketFacade).createProduct(captor.capture());
        Product capturedProduct = captor.getValue();
        assertTrue(10 == capturedProduct.getPrice());
        assertEquals(TITLE, capturedProduct.getTitle());
        assertEquals(DESC, capturedProduct.getDescription());
    }


}


