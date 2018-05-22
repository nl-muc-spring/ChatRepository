package com.netlight.munich.spring.server.backend;

import java.util.List;

public interface MessageService {
	Message addMessage(CreateMessageRequest messageRequest);
	
	List<Message> getAllMessages();
}
