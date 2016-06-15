package com.controllers;

import com.response.classes.Greeting;
import com.response.classes.HelloMessage;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Артем on 07.06.2016.
 */
//@RestController
@RequestMapping(name = "/Test")
public class PostController {
    @RequestMapping(name = "/ForTest", method = RequestMethod.POST)
    public String dataCopy(@RequestBody String content) {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.simpleCollectData();
        //mainTemplateJDBC.updateDatabase();
        BasicJsonParser basicJsonParser = new BasicJsonParser();
        Map<String, Object> parseMap = basicJsonParser.parseMap(content);
        for (Map.Entry<String, Object> entry : parseMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        String name = parseMap.get("name").toString();
        return content;
    }
}
