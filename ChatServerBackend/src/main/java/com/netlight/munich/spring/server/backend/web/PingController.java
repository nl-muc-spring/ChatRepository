package com.netlight.munich.spring.server.backend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@RequestMapping("/ping")
	public String ping() {
		return "ping";
	}
	
}
