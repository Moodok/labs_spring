package ua.nure.melnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import ua.nure.melnyk.dao.ProductDao;
import ua.nure.melnyk.storage.DbProductLoader;

@Component
public class DbMain {

    @Autowired
    private ProductDao jdbcProductDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private DbProductLoader dbProductLoader;

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        DbMain main = (DbMain) context.getBean("dbMain");
        execute(main);
    }

    public static void execute(DbMain main) throws InterruptedException {

    }

}
