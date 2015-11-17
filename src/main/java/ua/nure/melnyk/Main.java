package ua.nure.melnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.service.ProductService;
import ua.nure.melnyk.service.UserService;

@Component
public class Main {

    @Autowired
    private MarketFacade marketFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDao productDao;

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Main main = (Main) context.getBean("main");
        execute(main);
    }

    public static void execute(Main main) throws InterruptedException {
        while (true) {
            main.marketFacade.deleteUser(1);
            main.userService.delete(1);
            main.productDao.delete(1);
            Thread.sleep(3000);
        }
    }

}
