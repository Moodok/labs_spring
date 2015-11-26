package ua.nure.melnyk.integraion;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.entity.UserAccount;
import ua.nure.melnyk.exceptions.LowAmountException;
import ua.nure.melnyk.exceptions.LowBalanceException;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.facade.impl.MarketFacadeImpl;
import ua.nure.melnyk.service.ProductService;
import ua.nure.melnyk.service.UserAccountService;
import ua.nure.melnyk.service.UserService;

import static org.junit.Assert.assertEquals;

public class MarketIntegrationTest extends BaseIntegrationTest {

    private MarketFacade marketFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    private UserAccount buyerAccount;

    private UserAccount userAccount;

    private Product product = new Product();

    private User buyer = new User();

    private User seller = new User();

    @Before
    public void before() {
        product.setPrice(10);

        marketFacade = new MarketFacadeImpl(productService, userService, userAccountService);
    }


    @Test
    public void shouldBuyProduct() {
        marketFacade.createUser(buyer);
        marketFacade.updateBalance(10, buyer.getId());
        marketFacade.createUser(seller);
        product.setUserId(seller.getId());
        marketFacade.createProduct(product);

        marketFacade.buyProduct(product.getId(), buyer.getId());

        assertEquals(Integer.valueOf(10), marketFacade.getUserAccountByUserId(seller.getId()).getBalance());
        assertEquals(Integer.valueOf(0), marketFacade.getUserAccountByUserId(buyer.getId()).getBalance());
        assertEquals(Integer.valueOf(0), marketFacade.getProductById(product.getId()).getAmount());
    }

    @Test(expected = LowBalanceException.class)
    public void shouldGetLowBalance() {
        marketFacade.createUser(buyer);
        marketFacade.updateBalance(9, buyer.getId());
        marketFacade.createUser(seller);
        product.setUserId(seller.getId());
        marketFacade.createProduct(product);

        marketFacade.buyProduct(product.getId(), buyer.getId());
    }

}

