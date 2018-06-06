package com.netlight.munich.spring.server.backend.web.dto;

import javax.validation.constraints.NotNull;

public class CreateMessageRequest {
	@NotNull
	public String message;
	@NotNull
	public String nickName;
}
