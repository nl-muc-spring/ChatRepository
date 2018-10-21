package com.netlight.munich.spring.server.backend.web.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class UpdateUserRequest {
	@NotNull
	public LocalDateTime lastLogin;
}
