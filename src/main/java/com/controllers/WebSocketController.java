package com.controllers;

import com.response.classes.Greeting;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Артем on 07.06.2016.
 */
@RestController
public class WebSocketController {
    @MessageMapping("/hello/{groupName}/{deviceId}")
    @SendTo("/topic/{groupName}/{deviceId}")
    public Greeting greeting(@DestinationVariable String groupName, @DestinationVariable String deviceId) throws Exception {
        // TODO добавить в бд айди пользователя и группу нужную ему
        return new Greeting(groupName + " " + deviceId);
    }
}
