package com.netlight.munich.spring.server.app.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netlight.munich.spring.server.app.domain.Message;
import com.netlight.munich.spring.server.app.domain.User;
import com.netlight.munich.spring.server.app.storage.dto.StorageDtoCreateMessageRequest;
import com.netlight.munich.spring.server.app.storage.dto.StorageDtoCreateUserRequest;
import com.netlight.munich.spring.server.app.storage.dto.StorageDtoMessage;
import com.netlight.munich.spring.server.app.storage.dto.StorageDtoUpdateUserRequest;
import com.netlight.munich.spring.server.app.storage.dto.StorageDtoUser;

@Component
public class StorageAdapter {

	private final String storageHostname = "chat-server-backend";
	private final int storagePort = 8001;
	private final String storageUrl = String.format("http://%s:%d", storageHostname, storagePort);
	
	private final Logger log = Logger.getLogger(this.getClass().getName());
	
	private final RestTemplate restTemplate;
	
	public StorageAdapter(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setLastLoginDate(User user) {

		log.info("Setting last login date for user " + user.getUserName());
		
		StorageDtoUpdateUserRequest userUpdateRequest = new StorageDtoUpdateUserRequest();
		userUpdateRequest.lastLogin = LocalDateTime.now();
		
		HttpEntity<StorageDtoUpdateUserRequest> payload = new HttpEntity<>(userUpdateRequest);
		String resourceUrl = storageUrl + "/users/" + user.getUserName();
		ResponseEntity<StorageDtoUser> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, payload, StorageDtoUser.class);
		
		log.info("Done setting last login date for user " + user.getUserName());
	}
	
	public void saveMessage(Message message) {
		log.info("Saving message " + message.getMessage());
		StorageDtoCreateMessageRequest createMessageRequest = 
				new StorageDtoCreateMessageRequest();
		createMessageRequest.nickName = message.getUser().getUserName();
		createMessageRequest.message = message.getMessage();
		
		HttpEntity<StorageDtoCreateMessageRequest> payload = 
				new HttpEntity<>(createMessageRequest);
		String resourceUrl = storageUrl + "/messages";
		ResponseEntity<StorageDtoMessage> response = restTemplate.exchange(
				resourceUrl, HttpMethod.POST, payload, StorageDtoMessage.class);
		log.info("Done saving message " + message.getMessage());
	}
	
	public List<User> getUsers(){
		log.info("Requesting all users");
		
		String resourceUrl = storageUrl + "/users";
		ResponseEntity<List<StorageDtoUser>> response = restTemplate.exchange(
				resourceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<StorageDtoUser>>() {
		});
		log.info("Done requesting all users");
		
		List<StorageDtoUser> storageUsers = response.getBody();
		
		List<User> users = new ArrayList<>();
		for(StorageDtoUser storageUser : storageUsers) {
			User newUser = new User();
			newUser.setUserName(storageUser.getNickName());
			users.add(newUser);
		}
		return users;
	}
	
	public void createUser(User user){
		log.info("Creating new user " + user.getUserName());
		
		StorageDtoCreateUserRequest storageDtoCreateUserRequest = 
				new StorageDtoCreateUserRequest();
		storageDtoCreateUserRequest.nickName = user.getUserName();
		
		HttpEntity<StorageDtoCreateUserRequest> payload = new HttpEntity<>(
				storageDtoCreateUserRequest);
		
		String resourceUrl = storageUrl + "/users";
		ResponseEntity<StorageDtoUser> response = restTemplate.exchange(
				resourceUrl, HttpMethod.POST, payload, StorageDtoUser.class);
		
		log.info("Done creating new user " + user.getUserName());
	}
	
	
}
