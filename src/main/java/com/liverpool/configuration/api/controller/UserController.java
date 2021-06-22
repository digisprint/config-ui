package com.liverpool.configuration.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.api.bean.LoginPayload;
import com.liverpool.configuration.api.bean.User;
import com.liverpool.configuration.api.bean.UserResponse;
import com.liverpool.configuration.api.bean.Users;
import com.liverpool.configuration.api.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value="/user")
	public Users getUsers() {
		
		return userService.getUsers();
	}
	
	@PostMapping(value="/register")
	public String register(@RequestBody @Valid User user) {
		return userService.addUser(user);
	}
	
	@PostMapping(value="/login")
	public UserResponse login(@RequestBody @Valid LoginPayload loginPayload) {
		return userService.login(loginPayload.getUserName(), loginPayload.getPassword());
		
		
	}
	
	
}
