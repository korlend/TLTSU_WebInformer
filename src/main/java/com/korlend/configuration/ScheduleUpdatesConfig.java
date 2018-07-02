package com.korlend.configuration;

import com.korlend.CRUD.MainTemplateJDBC;
import com.korlend.CRUD.tables.custom.ConnectedUsers;
import com.korlend.CRUD.tables.custom.CustomContentOfSchedule;
import com.korlend.classes.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(fixedRate = 600000, initialDelay = 0)
    public void updateDatabase() {
        List<ConnectedUsers> connectedUsersList = mainTemplateJDBC.getConnectedUsersDAO().findAllMySQL();
        mainTemplateJDBC.updateDatabase().forEach((group) -> {
            List<CustomContentOfSchedule> schedule = mainTemplateJDBC.findAllMySQL(group);
            simpMessagingTemplate.convertAndSend("/topic/" + group, new Message(schedule));
            connectedUsersList.forEach(r -> {
                if (r.getPreferred_groups().contains(group))
                    simpMessagingTemplate.convertAndSend("/topic/" + r.getDevice_id(),
                            new Message(schedule));
            });
        });
    }
}
