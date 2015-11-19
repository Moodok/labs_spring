package ua.nure.melnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.facade.MarketFacade;
import ua.nure.melnyk.service.UserService;

@Component
public class DbMain {

    @Autowired
    private ProductDao jdbcProductDao;

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DbMain main = (DbMain) context.getBean("dbMain");
        execute(main);
    }

    public static void execute(DbMain main) throws InterruptedException {
        Product product = new Product();
        product.setTitle("title");
        product.setPrice(10);
        main.jdbcProductDao.create(product);
        System.out.println(product);

    }

}
