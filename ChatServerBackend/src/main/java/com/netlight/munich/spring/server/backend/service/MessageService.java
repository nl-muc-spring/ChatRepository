package com.netlight.munich.spring.server.backend.service;

import java.util.List;

import com.netlight.munich.spring.server.backend.domain.Message;
import com.netlight.munich.spring.server.backend.web.dto.CreateMessageRequest;

public interface MessageService {
	Message addMessage(CreateMessageRequest request);
	List<Message> getAllMessages();
	List<Message> getAllMessagesByUser(String nickName);
}
