package com.netlight.munich.spring.server.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.netlight.munich.spring.server.backend.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	Optional<User> findByNickName(String nickName);
}
