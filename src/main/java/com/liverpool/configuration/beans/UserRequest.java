package com.liverpool.configuration.beans;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private List<Role> roles;
	private Map<String, String> accessPrivileges;
	
}
