package com;

/**
 * Created by Артем on 30.04.2016.
 */
import java.util.concurrent.atomic.AtomicLong;

import CRUD.MainTemplateJDBC;
import CRUD.StudentJDBCTemplate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@EnableAutoConfiguration
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/DataCopy")
    public DataCopy dataCopy() {
        return new DataCopy(mainTemplateJDBC);
    }

    @RequestMapping("/DataSelectOracle")
    public DataSelectOracle dataSelectOracle() {
        return new DataSelectOracle(mainTemplateJDBC);
    }

    @RequestMapping("/DataSelectMySQL")
    public DataSelectMySQL dataSelectMySQL() {
        return new DataSelectMySQL(mainTemplateJDBC);
    }
}