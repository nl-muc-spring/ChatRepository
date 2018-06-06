package com.netlight.munich.spring.server.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.netlight.munich.spring.server.backend.domain.Message;
import com.netlight.munich.spring.server.backend.repository.MessageRepository;
import com.netlight.munich.spring.server.backend.web.dto.CreateMessageRequest;

@Component
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;
	private final UserService userService;
	
	public MessageServiceImpl(@Qualifier("UserServiceImpl") UserService userService,
			MessageRepository messageRepository) {
		this.userService = userService;
		this.messageRepository = messageRepository;
	}

	@Override
	public Message addMessage(CreateMessageRequest request) {
		Message message = new Message();
		message.setMessage(request.message);
		message.setUser(userService.getUserByNickName(request.nickName));
		message.setCreatedAt(LocalDateTime.now());
		messageRepository.save(message);
		return message;
	}

	@Override
	public List<Message> getAllMessages() {
		List<Message> messageList = new ArrayList<>();
		messageRepository.findAll().forEach(messageList::add);
		return messageList;
	}

	@Override
	public List<Message> getAllMessagesByUser(String nickName) {
		return messageRepository.findByUser_NickName(nickName);
	}

}
