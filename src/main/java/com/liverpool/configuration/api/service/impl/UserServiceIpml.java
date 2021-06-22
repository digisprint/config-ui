package com.liverpool.configuration.api.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.api.bean.User;
import com.liverpool.configuration.api.bean.UserResponse;
import com.liverpool.configuration.api.bean.Users;
import com.liverpool.configuration.api.repository.UserRepository;
import com.liverpool.configuration.api.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserServiceIpml implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Users getUsers() {
		List<User> userList = userRepo.findAll();
		Users u = new Users();
		u.setUsers(userList);

		return u;

	}

	@Override
	public String addUser(User user) {

		String encryptedPassword = encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);
		
			userRepo.insert(user);
			return "sucessfully registered " + user.getUserName();


	}

	@Override
	public UserResponse login(String userName, String password) {

			
			User user = userRepo.findByUserName(userName);
			if(user == null) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User name oes not exist");
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
			if(isPasswordMatch) {
			UserResponse userresponse = new UserResponse();
				userresponse.setStatus(Boolean.TRUE);
				userresponse.setToken(generateToken(user));
			return userresponse;
			}else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User name or password is wrong");
			}
		
	}
	
	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" ;//+ "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();

	}
	private String encryptPassword(String password)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		
		return encodedPassword;
		
	}
	
	private String generateToken(User user) {
		Calendar currentTimeNow = Calendar.getInstance();
		currentTimeNow.add(Calendar.MINUTE, 30);
		Date expireTime = currentTimeNow.getTime();
		JwtBuilder token = Jwts.builder()
		.setIssuer("CongfigServer")
		.setSubject("ConfigLogin")
		.claim("userName", user.getUserName())
		.claim("userId", user.getId())
		.claim("role", "admin")
		.setIssuedAt(currentTimeNow.getTime())
		.setExpiration(expireTime)
		.signWith(SignatureAlgorithm.HS512,
			    "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=");
		
		return token.compact();
	}
		
}
