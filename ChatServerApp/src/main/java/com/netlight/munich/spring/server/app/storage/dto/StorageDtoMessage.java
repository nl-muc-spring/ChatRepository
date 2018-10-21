package com.netlight.munich.spring.server.app.storage.dto;

import java.time.LocalDateTime;


public class StorageDtoMessage {
	
	private String id;
	private StorageDtoUser user;
	private String message;
	private LocalDateTime createdAt;

	public StorageDtoUser getUser() {
		return user;
	}
	public void setUser(StorageDtoUser user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
