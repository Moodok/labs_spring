package ua.nure.melnyk.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.facade.MarketFacade;

import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidatorTest {

    @InjectMocks
    private ProductValidator productValidator = new ProductValidator();

    @Mock
    private MarketFacade marketFacade;

    @Mock
    private Errors errors;

    @Test
    public void shouldSupportProduct() {
        assertTrue(productValidator.supports(Product.class));
    }

    @Test
    public void shouldNotPassValidation() {
        when(marketFacade.getProductsByTitle(any(String.class))).thenReturn(Collections.singletonList(new Product()));
        productValidator.validate(new Product(), errors);
        verify(marketFacade).getProductsByTitle(any(String.class));
        verify(errors).rejectValue(any(String.class), any(String.class), any(String.class));
    }

}
