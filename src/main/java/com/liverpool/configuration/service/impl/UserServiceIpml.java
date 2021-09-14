package com.liverpool.configuration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.beans.UserRequest;
import com.liverpool.configuration.beans.Users;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.UserService;

@Service
public class UserServiceIpml implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepository;
	
	
	public UserServiceIpml(UserRepository userRepo,
			RoleRepository roleRepository) {
		this.userRepo = userRepo;
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
	public String updateUser(UserRequest userReq) {
		User user = new User();
		BeanUtils.copyProperties(userReq, user);
		String encryptedPassword = encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepo.save(user);
		return "Successfully updated " +user.getUserName();
	}

	@Override
	public String deleteUser(String userId) {
		userRepo.deleteById(userId);
		return "Successfully deleted " +userId;
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