package com.liverpool.configuration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.Roles;
import com.liverpool.configuration.repository.RoleRepository;
import com.liverpool.configuration.service.RoleService;

@SpringBootTest
public class RoleServiceImplTest {
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void getRoles() {
		List<Role> roleList=new ArrayList<>();
		Role r = new Role();
		roleList.add(r);
		when(roleRepository.findAll()).thenReturn(roleList);
		assertThat(roleService.getRoles()).isNotNull();
	}
	
	@Test
	public void addRole() {
		Role r = new Role();
		when(roleRepository.insert(any(Role.class))).thenReturn(new Role());
		assertThat(roleService.addRole(r)).isNotNull();
	}
	@Test
	public void deleteRole() {
		doNothing().when(roleRepository).deleteById(anyString());
		roleService.deleteRole("r");
		
	}
	@Test
	public void updateRole() {
		Role r = new Role();
		when(roleRepository.insert(any(Role.class))).thenReturn(new Role());
		assertThat(roleService.updateRole(r)).isNotNull();
		
	}
	@Test
	public void getRoleById() {
		Role role = new Role("1234","ADMIN");
		when(roleRepository.findById(role.getId())).thenReturn(Optional.of(new Role()));
		assertThat(roleService.getRoleById(role.getId())).isNotNull();
	}
}
