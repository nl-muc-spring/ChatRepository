package com.netlight.munich.spring.server.backend.service;

import java.util.List;

import com.netlight.munich.spring.server.backend.domain.User;
import com.netlight.munich.spring.server.backend.web.dto.CreateUserRequest;

public interface UserService {
	User addUser(CreateUserRequest createUserRequest);
	void saveUser(User user);
	User getUserByNickName(String nickName);
	List<User> getAllUsers();
	void updateLastLoggedIn(User user);
}
