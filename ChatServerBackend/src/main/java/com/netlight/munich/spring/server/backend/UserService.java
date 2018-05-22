package com.netlight.munich.spring.server.backend;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();
	User addUser(CreateUserRequest request);
	User getUserByNickName(String nickName);
}
