package com.configuration;

import com.response.classes.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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


    @Scheduled(fixedRate = 600000, initialDelay = 60000)
    public void updateDatabase() {
        //TODO добавить функцию обновления mysql из oracle
    }

    /**
     * просто для тестирования
     */
    @Scheduled(fixedRate = 5000)
    public void update() {
        //simpMessagingTemplate.convertAndSend("/topic/" + "кек", new Greeting("t-pain"));
        //System.out.println("updated");
    }
}
