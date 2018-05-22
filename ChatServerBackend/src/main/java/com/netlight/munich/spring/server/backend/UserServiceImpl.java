package com.netlight.munich.spring.server.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
	private final List<User> users;
	
	public UserServiceImpl() {
		users = new ArrayList<>();
	}
	
	@Override
	public User addUser(CreateUserRequest request) {
		User user = new User();
		user.setNickName(request.nickName);
		user.setCreatedAt(LocalDateTime.now());
		users.add(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public User getUserByNickName(String nickName) {
		return users.stream().filter(user -> nickName.equals(user.getNickName())).findFirst().orElse(null);
	}
}
