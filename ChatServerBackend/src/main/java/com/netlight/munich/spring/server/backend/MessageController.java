package com.netlight.munich.spring.server.backend;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/messages")
	public Message createMessage(@RequestBody CreateMessageRequest request) {
		return messageService.addMessage(request);
	}
	
	@RequestMapping("/messages")
	public List<Message> getAllMessages() {
		return messageService.getAllMessages();
	}
}
