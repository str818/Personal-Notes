
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-ioc.xml");
        TestA test = (TestA) context.getBean("testA");
        System.out.println(test.myTestB.s);
    }
}
