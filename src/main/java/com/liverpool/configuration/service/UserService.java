package com.liverpool.configuration.service;

import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserRequest;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;

public interface UserService {
	
	Users getUsers();

	String addUser(User user);
	
	String updateUser(UserRequest userReq);
	
	String deleteUser(String userId);

	UserResponse login(String userName, String password);

	void insertInitialData();
	
}
