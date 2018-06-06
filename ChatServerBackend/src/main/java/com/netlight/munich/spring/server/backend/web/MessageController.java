package com.netlight.munich.spring.server.backend.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netlight.munich.spring.server.backend.domain.Message;
import com.netlight.munich.spring.server.backend.domain.User;
import com.netlight.munich.spring.server.backend.service.MessageService;
import com.netlight.munich.spring.server.backend.service.UserService;
import com.netlight.munich.spring.server.backend.web.dto.CreateMessageRequest;

@RestController
public class MessageController {

	private final MessageService messageService;
	private final UserService userService;
	
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping("/messages")
	public Message addMessage(@RequestBody @Validated CreateMessageRequest request) {
		User user = userService.getUserByNickName(request.nickName);
		userService.updateLastLoggedIn(user);
		return messageService.addMessage(request);
	}

	@GetMapping("/messages")
	public List<Message> getMessages(@PathParam(value = "nickName") String nickName) {
		if (nickName != null) {
			return messageService.getAllMessagesByUser(nickName);
		}
		else {
			return messageService.getAllMessages();
		}
	}
	
}
