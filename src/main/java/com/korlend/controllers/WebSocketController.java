package com.korlend.controllers;

import com.korlend.CRUD.MainTemplateJDBC;
import com.korlend.classes.HelloMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
}
