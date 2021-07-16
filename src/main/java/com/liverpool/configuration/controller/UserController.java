package com.liverpool.configuration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.beans.LoginPayload;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserRequest;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;
import com.liverpool.configuration.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/user")
	public Users getUsers() {
		return userService.getUsers();
	}

	@PostMapping(value = "/register")
	public String register(@RequestBody @Valid User user) {
		return userService.addUser(user);
	}

	@PostMapping(value = "/login")
	public UserResponse login(@RequestBody @Valid LoginPayload loginPayload) {
		return userService.login(loginPayload.getUserName(), loginPayload.getPassword());
	}
	
	@PutMapping("/user")
	public String updateUser(@RequestBody UserRequest userReq) {
		return userService.updateUser(userReq);
	}
	
}
