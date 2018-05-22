package com.netlight.munich.spring.server.backend;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody CreateUserRequest request) {
		return userService.addUser(request);
	}
	
	@RequestMapping("/users")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{nickName}")
	public User getUserByNickName(@PathVariable String nickName) {
		return userService.getUserByNickName(nickName);
	}
}
