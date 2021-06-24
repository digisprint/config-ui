package com.liverpool.configuration.service;

import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;

public interface UserService {

	
	Users getUsers();
	String addUser(User user);
	UserResponse login(String userName,String password);
}
