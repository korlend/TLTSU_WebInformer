package com.korlend.configuration;

import com.korlend.CRUD.MainTemplateJDBC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.Map;

public class UserChannelInterceptor extends ChannelInterceptorAdapter {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        if (message.getHeaders().get("simpMessageType").toString().equals("SUBSCRIBE")) {
            String[] dest = ((Map)message.getHeaders().
                    get("nativeHeaders")).
                    get("destination").toString().split("/");
            if (dest.length == 3 && dest[2].length() > 15) {
                ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
                MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");
                mainTemplateJDBC.addConnUser(dest[2].substring(0, dest[2].length() - 1), "[]");
            }
        }
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        return message;
    }
}