package com.netlight.munich.spring.server.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.netlight.munich.spring.server.backend.domain.Message;

public interface MessageRepository extends CrudRepository<Message, String> {
	List<Message> findByUser_NickName(String nickName);
}
