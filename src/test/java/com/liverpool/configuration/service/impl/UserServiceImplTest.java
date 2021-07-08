package com.liverpool.configuration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.UserService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UserServiceIpml.class })
public class UserServiceImplTest {
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private ConfigrationsProeprties properties;
	
	@MockBean
	private RoleRepository roleRepository;
	
	private UserService userService;
	
	@MockBean
	private User user;
	
	@BeforeEach
	 public void setUp() {
		userService = new UserServiceIpml(userRepo, properties, roleRepository);
	 }
	
	@Test
	public void getUser() {
		List<User> userList=new ArrayList<>();
		User u = new User();
		userList.add(u);
		when(userRepo.findAll()).thenReturn(userList);
		assertThat(userService.getUsers()).isNotNull();
	}

    @Test
    public void addUser() {
        User u= new User();
        u.setPassword("test");
        Role r = new Role();
        r.setId("1234567");
        r.setRoleName("test");
        List<Role> roleList = new ArrayList<>();
        roleList.add(r);
        u.setRoles(roleList);
        when(userRepo.insert(any(User.class))).thenReturn(new User());
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(new Role()));
        assertThat(userService.addUser(u)).isNotNull();
    }
    
    @Test
    public void addUserWithNoRoles() {
        User u= new User();
        Role r = new Role();
        r.setId("1234567");
        r.setRoleName("test");
        List<Role> roleList = new ArrayList<>();
        roleList.add(r);
        u.setRoles(roleList);
        when(userRepo.insert(any(User.class))).thenReturn(new User());
       assertThrows(ResponseStatusException.class, ()->{
    	   userService.addUser(u);
       });
    }
    
    @Test
    public void login() {
		when(userRepo.findByUserName(anyString())).thenReturn(user);
		when(user.getPassword()).thenReturn("$2a$10$C9C892lQUx2R7xJMzsUrDeJtvJ6ju20bZ4.LmyAIhhCXYdxWIVACG");
		when(properties.getSecretKey()).thenReturn("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=");
		assertThat(userService.login("test","admin")).isNotNull();
    }
    
    @Test
    public void loginWithNoPasswordMathch() {
    	when(userRepo.findByUserName(anyString())).thenReturn(user);
    	assertThrows(ResponseStatusException.class, ()->{
    		userService.login("test","admin");
    	});
    }
    
    @Test
    public void loginWithNoUser() {
    	when(userRepo.findByUserName(anyString())).thenReturn(null);
    	assertThrows(ResponseStatusException.class, ()->{
    		userService.login("test","admin");
    	});
    }
}