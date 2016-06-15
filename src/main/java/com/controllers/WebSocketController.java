package com.controllers;

import CRUD.MainTemplateJDBC;
import com.response.classes.Greeting;
import com.response.classes.HelloMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Артем on 07.06.2016.
 */

@RestController
public class WebSocketController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @MessageMapping("/hello")
    public HelloMessage greeting(HelloMessage helloMessage) throws Exception {
        mainTemplateJDBC.getConnectedUsersDAO().addGroupToUser(
                helloMessage.getId(),
                helloMessage.getGroup());
        return helloMessage;
    }
    /*
    @MessageMapping("/hello/{groupName}/{deviceId}")
    @SendTo("/topic/{groupName}/{deviceId}")
    public Greeting greeting(@DestinationVariable String groupName, @DestinationVariable String deviceId) throws Exception {
        // TODO добавить в бд айди пользователя и группу нужную ему
        return new Greeting(groupName + " " + deviceId);
    }
    */
}
