package com.liverpool.configuration.api.service;

import com.liverpool.configuration.api.bean.User;
import com.liverpool.configuration.api.bean.Users;

public interface UserService {

	
	Users getUsers();
	String addUser(User user);
	User login(String userName,String password);
}
