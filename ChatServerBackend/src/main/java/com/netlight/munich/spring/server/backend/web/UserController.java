package com.netlight.munich.spring.server.backend.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netlight.munich.spring.server.backend.domain.User;
import com.netlight.munich.spring.server.backend.service.UserService;
import com.netlight.munich.spring.server.backend.web.dto.CreateUserRequest;
import com.netlight.munich.spring.server.backend.web.dto.UpdateUserRequest;

@RestController
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public User createUser(@RequestBody @Validated CreateUserRequest request) {
		return userService.addUser(request);
	}
	
	@PutMapping("/users/{nickName}")
	public User updateUser(@PathVariable String nickName,
			@RequestBody @Validated UpdateUserRequest request) {
		User user = userService.getUserByNickName(nickName);
		user.setLastLogin(request.lastLogin);
		userService.saveUser(user);
		return user;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{nickName}")
	public User getUser(@PathVariable String nickName) {
		return userService.getUserByNickName(nickName);
	}	
}
