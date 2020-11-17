package homework.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //xml
        Student student01 = (Student) context.getBean("student01");
        System.out.println(student01);
        //注解
        School school = (School) context.getBean("school");
        school.ding();
    }
}
