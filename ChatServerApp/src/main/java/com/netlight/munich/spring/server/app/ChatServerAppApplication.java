package com.netlight.munich.spring.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ChatServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServerAppApplication.class, args);
	}
}
