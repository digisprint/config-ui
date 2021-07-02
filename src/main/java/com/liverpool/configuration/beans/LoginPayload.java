package com.liverpool.configuration.beans;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPayload {
	
	@NotNull
	private String userName;
	@NotNull
	private String password;
}
