package com.netlight.munich.spring.server.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.netlight.munich.spring.server.backend.domain.User;
import com.netlight.munich.spring.server.backend.repository.UserRepository;
import com.netlight.munich.spring.server.backend.web.dto.CreateUserRequest;

@Component("UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final Logger log = Logger.getLogger(this.getClass().getName());

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User addUser(CreateUserRequest request) {
		User user = new User(request.nickName);
		user.setCreatedAt(LocalDateTime.now());
		user.setLastLogin(null);
		log.info("Saving user to database: " + user.getNickName());
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		log.info("Retrieving all users from database");
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}

	@Override
	public User getUserByNickName(String nickName) {
		log.info("Retrieving user " + nickName);
		Optional<User> user = userRepository.findByNickName(nickName);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			throw new IllegalArgumentException("We dont have that nickname :(");
		}
	}

	@Override
	public void updateLastLoggedIn(User user) {
		log.info("Updating last login for user " + user.getNickName());
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
