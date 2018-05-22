package com.netlight.munich.spring.server.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService{
	private final List<Message> messages;
	
	private final UserService userService;
	
	public MessageServiceImpl(UserService userService) {
		this.userService = userService;
		messages = new ArrayList<>();
	}

	@Override
	public Message addMessage(CreateMessageRequest messageRequest) {
		Message message = new Message();
		message.setContent(messageRequest.content);
		message.setUser(userService.getUserByNickName(messageRequest.nickName));
		message.setCreatedAt(LocalDateTime.now());
		messages.add(message);
		return message;
	}

	@Override
	public List<Message> getAllMessages() {
		return messages;
	}
}
