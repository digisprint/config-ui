package com.liverpool.configuration.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.liverpool.configuration.api.bean.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUserNameAndPassword(String userName, String password);
	User findByUserName(String userName);
}
