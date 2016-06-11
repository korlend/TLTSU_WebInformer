package com.configuration;

import CRUD.MainTemplateJDBC;
import com.response.classes.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Артем on 10.06.2016.
 */

@Configuration
@EnableAsync
@EnableScheduling
public class ScheduleUpdatesConfig {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @Scheduled(fixedRate = 600000, initialDelay = 60000)
    public void updateDatabase() {
        for(String group : mainTemplateJDBC.updateDatabase()) {
            simpMessagingTemplate.convertAndSend("/topic/" + group, mainTemplateJDBC.findAllMySQL(group));
        }
    }

    /**
     * просто для тестирования
     */
    @Scheduled(fixedRate = 10000)
    public void update() {
        //simpMessagingTemplate.convertAndSend("/topic/" + "1", new Greeting("t-pain1"));
        //mainTemplateJDBC.updateDatabase();
        //System.out.println("updated");
    }
}
