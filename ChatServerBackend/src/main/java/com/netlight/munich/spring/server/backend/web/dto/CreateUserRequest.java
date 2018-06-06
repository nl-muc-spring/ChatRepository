package com.netlight.munich.spring.server.backend.web.dto;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
	@NotNull
	public String nickName;
}
