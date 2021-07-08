package com.liverpool.configuration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.liverpool.configuration.beans.Role;
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
	public void addRoles() {
		Role r= new Role();
		when(roleRepository.insert(any(Role.class))).thenReturn(new Role());
		assertThat(roleService.addRole(r)).isNotNull();
	}
	
	@Test
	public void deleteRoles() {
	    doNothing().when(roleService).deleteRole(anyString());
	}
	
    @Test
    public void updateRoles() {
    	Role r = new Role();
    	when(roleRepository.insert(any(Role.class))).thenReturn(new Role());
		assertThat(roleService.updateRole(r)).isNotNull();
    }
    
    @Test
    public void getRoleById() {
        Role role = new Role();
        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(new Role()));
        assertThat(roleService.getRoleById(role.getId())).isNotNull();
    }
}	