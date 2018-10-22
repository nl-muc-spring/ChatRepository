package com.netlight.munich.spring.server.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.netlight.munich.spring.server.app.domain.Message;
import com.netlight.munich.spring.server.app.domain.User;
import com.netlight.munich.spring.server.app.domain.UserDto;
import com.netlight.munich.spring.server.app.storage.StorageAdapter;

@Controller
public class MessageController {

    List<String> userNamesInUse = new ArrayList();
    private final StorageAdapter storageAdapter;
	private final Logger log = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    public MessageController(StorageAdapter storageAdapter) {
		this.storageAdapter = storageAdapter;
	}

	@MessageMapping("/meassageroom")
    @SendTo("/topic/messages")
    public Message messageroom(Message message) throws Exception {
		message.setDate(new Date());
		storageAdapter.saveMessage(message);
        return message;
    }

    @MessageMapping("/connected")
    public void connect(UserDto userDto) {
    	if (!userNamesInUse.contains(userDto.getUserName())) {
            userNamesInUse.add(userDto.getUserName());

    		List<User> existingUsers = storageAdapter.getUsers();
    		List<String> existingUserNames = existingUsers.stream()
    				.map(user -> user.getUserName())
    				.collect(Collectors.toList());
			User newUser = new User();
			newUser.setUserName(userDto.getUserName());
    		if (!existingUserNames.contains(userDto.getUserName())) {
    			storageAdapter.createUser(newUser);
    		}
    		storageAdapter.setLastLoginDate(newUser);
    		
        }
        log.info("Current users: " +  String.join(";", userNamesInUse));
    }

    @MessageMapping("/disconnected")
    public void disconnect(UserDto userDto) {
        userNamesInUse.remove(userDto.getUserName());
        log.info("Current users: " +  String.join(";", userNamesInUse));
    }
}
