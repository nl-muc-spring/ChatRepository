package com.netlight.munich.spring.server.app.storage.dto;

import javax.validation.constraints.NotNull;

public class StorageDtoCreateMessageRequest {
	@NotNull
	public String message;
	@NotNull
	public String nickName;
}
