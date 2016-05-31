package com;

/**
 * Created by ����� on 22.04.2016.
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //ApplicationContext context =
        //        new ClassPathXmlApplicationContext("Beans.xml");

        //StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
        Locale.setDefault(new Locale("en", "US"));
        SpringApplication.run(Application.class, args);

    }
}
