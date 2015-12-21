package ua.nure.melnyk.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.facade.MarketFacade;

import java.util.List;

@Component
public class ProductValidator implements Validator {

    @Autowired
    private MarketFacade marketFacade;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Product.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        List<Product> productsByTitle = marketFacade.getProductsByTitle(product.getTitle());
        if (!productsByTitle.isEmpty())
            errors.rejectValue("title", "title.taken", "title is taken");
    }

}
