package com;

/**
 * Created by Артем on 30.04.2016.
 */
import java.util.concurrent.atomic.AtomicLong;

import CRUD.MainTemplateJDBC;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
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
    public DataSelectMySQL dataSelectMySQL(@RequestParam(value="group", defaultValue="") String group,
                                           @RequestParam(value="starton", defaultValue="") String starton,
                                           @RequestParam(value="endon", defaultValue="") String endon) {
        if (!group.isEmpty()) {
            if (!starton.isEmpty() && !endon.isEmpty()) {
                return new DataSelectMySQL(mainTemplateJDBC, group, starton, endon);
            }
            return new DataSelectMySQL(mainTemplateJDBC, group);
        }
        return new DataSelectMySQL(mainTemplateJDBC);
    }
}