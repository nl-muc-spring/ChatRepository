package com.netlight.munich.spring.server.app.storage.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class StorageDtoUpdateUserRequest {
	@NotNull
	public LocalDateTime lastLogin;
}
