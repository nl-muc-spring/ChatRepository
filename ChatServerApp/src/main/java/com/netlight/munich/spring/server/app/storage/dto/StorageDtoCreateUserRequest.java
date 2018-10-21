package com.netlight.munich.spring.server.app.storage.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class StorageDtoCreateUserRequest {
	@NotNull
	public String nickName;
}
