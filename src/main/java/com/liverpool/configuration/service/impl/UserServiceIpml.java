package com.liverpool.configuration.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceIpml implements UserService {

	private UserRepository userRepo;

	private ConfigrationsProeprties properties;

	private RoleRepository roleRepository;
	
	public UserServiceIpml(UserRepository userRepo, ConfigrationsProeprties properties, RoleRepository roleRepository) {
		this.userRepo = userRepo;
		this.properties=properties;
		this.roleRepository=roleRepository;
	}
	
	@Override
	public Users getUsers() {
		List<User> userList = userRepo.findAll();
		Users u = new Users();
		u.setUsers(userList);

		return u;

	}

	@Override
	public String addUser(User user) {
		AtomicBoolean allRolesAvaialble = new AtomicBoolean(true);
		user.getRoles().stream().forEach(role->{
			if(!roleRepository.findById(role.getId()).isPresent()) {
				allRolesAvaialble.set(false);
			}
		});
		if(allRolesAvaialble.get()) {
		String encryptedPassword = encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);

		userRepo.insert(user);
		return "sucessfully registered " + user.getUserName();
		
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of the Roles are not found");
		}

	}

	@Override
	public UserResponse login(String userName, String password) {

		User user = userRepo.findByUserName(userName);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User name oes not exist");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
		if (isPasswordMatch) {
			UserResponse userresponse = new UserResponse();
			userresponse.setStatus(Boolean.TRUE);
			userresponse.setToken(generateToken(user));
			return userresponse;
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User name or password is wrong");
		}

	}

	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])";// + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();

	}

	public String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);

		return encodedPassword;

	}

	private String generateToken(User user) {
		Calendar currentTimeNow = Calendar.getInstance();
		currentTimeNow.add(Calendar.MINUTE, 30);
		Date expireTime = currentTimeNow.getTime();
		JwtBuilder token = Jwts.builder().setIssuer("CongfigServer").setSubject("ConfigLogin")
				.claim("userName", user.getUserName()).claim("userId", user.getId()).claim("roles", user.getRoles())
				.setIssuedAt(currentTimeNow.getTime()).setExpiration(expireTime)
				.signWith(SignatureAlgorithm.HS512, properties.getSecretKey().getBytes());

		return token.compact();

	}

	public void insertInitialData() {
		Optional<User> user = userRepo.findById("admin");
		if (!user.isPresent()) {
			User u = new User();
			u.setId("admin");
			u.setFirstName("andy");
			u.setLastName("andy");
			u.setEmail("");
			u.setPassword(encryptPassword("admin"));
			u.setUserName("admin");
			List<Role> roles = new ArrayList<>();
			Role role = new Role();
			role.setId("admin");
			role.setRoleName("super admin");
			roleRepository.insert(role);
			roles.add(role);
			u.setRoles(roles);
			userRepo.insert(u);
		}

	}
}
