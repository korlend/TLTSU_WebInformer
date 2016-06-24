package com.configuration;

import CRUD.MainTemplateJDBC;
import CRUD.tables.custom.ConnectedUsers;
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

import java.util.ArrayList;
import java.util.List;

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

    //@Scheduled(fixedRate = 600000, initialDelay = 600)
    public void updateDatabase() {

        mainTemplateJDBC.updateDatabase().stream().forEach((group) -> {
            System.out.println(group);
            //simpMessagingTemplate.convertAndSendToUser();
        });

    }

    /*
     * для тестирования
    */
    //@Scheduled(fixedRate = 30000, initialDelay = 5000)
    public void update() {
        for (ConnectedUsers entry : mainTemplateJDBC.getConnectedUsersDAO().findAllMySQL()) {
            simpMessagingTemplate.convertAndSend("/topic/" + entry.getDevice_id(),
                    new Greeting(entry.getPreferred_groups().toString()));
        }
    }

}
