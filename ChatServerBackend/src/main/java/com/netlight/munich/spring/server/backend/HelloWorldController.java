package com.netlight.munich.spring.server.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@RequestMapping("/helloworld")
	public String helloWorld() {
		return "Hello World!";
	}
}
