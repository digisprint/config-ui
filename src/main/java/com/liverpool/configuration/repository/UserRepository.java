package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUserNameAndPassword(String userName, String password);
	User findByUserName(String userName);
}
