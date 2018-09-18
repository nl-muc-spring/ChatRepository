package com.netlight.munich.spring.server.app.web;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.netlight.munich.spring.server.app.domain.Message;

@Controller
public class MessageController {

	@MessageMapping("/meassageroom")
    @SendTo("/topic/messages")
    public Message messageroom(Message message) throws Exception {
		message.setDate(new Date());
        return message;
    }
}
