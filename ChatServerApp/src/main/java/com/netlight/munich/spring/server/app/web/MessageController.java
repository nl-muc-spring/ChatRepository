package com.netlight.munich.spring.server.app.web;

import com.netlight.munich.spring.server.app.domain.UserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.netlight.munich.spring.server.app.domain.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class MessageController {

    List<String> userNamesInUse = new ArrayList();

	@MessageMapping("/meassageroom")
    @SendTo("/topic/messages")
    public Message messageroom(Message message) throws Exception {
        return message;
    }

    @MessageMapping("/connected")
    public void connect(UserDto userDto) {
        if (userNamesInUse.contains(userDto.getUserName())) {
            return;
        } else {
            userNamesInUse.add(userDto.getUserName());
        }
        System.out.println("Current users: " +  String.join(";", userNamesInUse));
    }

    @MessageMapping("/disconnected")
    public void disconnect(UserDto userDto) {
        userNamesInUse.remove(userDto.getUserName());
        System.out.println("Current users: " +  String.join(";", userNamesInUse));
    }
}
