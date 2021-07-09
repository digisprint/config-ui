package com.liverpool.configuration.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserResponse;
import com.liverpool.configuration.beans.Users;
import com.liverpool.configuration.config.JwtTokenUtil;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceIpml implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ConfigrationsProeprties properties;
	
	@Autowired
	private JwtTokenUtil jwtUtils;

	public UserServiceIpml(UserRepository userRepo, ConfigrationsProeprties properties,
			RoleRepository roleRepository) {
		this.userRepo = userRepo;
		this.properties = properties;
		this.roleRepository = roleRepository;
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
		AtomicBoolean allRolesAvailable = new AtomicBoolean(true);
		user.getRoles().stream().forEach(role -> {
			if (!roleRepository.findById(role.getId()).isPresent()) {
				allRolesAvailable.set(false);
			}
		});
		if (allRolesAvailable.get()) {
			String encryptedPassword = encryptPassword(user.getPassword());
			user.setPassword(encryptedPassword);

			userRepo.insert(user);
			return "sucessfully registered " + user.getUserName();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "some of the roles are not found");
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
			userresponse.setToken(jwtUtils.generateToken(user));
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

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}

	public void insertInitialData() {
		Optional<User> user = userRepo.findById("admin");
		if (!user.isPresent()) {
			User u = new User();
			u.setUserName("admin");
			u.setPassword("admin");
			u.setFirstName("ADMIN");
			u.setLastName("ADMIN");
			u.setEmail("admin@test.com");
			u.setId("admin");
			List<Role> roles = new ArrayList<>();
			Role role = new Role();
			role.setId("admin");
			role.setRoleName("Super admin");
			u.setRoles(roles);
			roleRepository.insert(role);
			roles.add(role);
			u.setRoles(roles);
			addUser(u);
		}
	}
}