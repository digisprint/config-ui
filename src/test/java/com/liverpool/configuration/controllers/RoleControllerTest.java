package com.liverpool.configuration.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.beans.Role;
import com.liverpool.configuration.beans.Roles;
import com.liverpool.configuration.service.RoleService;

@SpringBootTest
@ActiveProfiles("junit")
public class RoleControllerTest {
	
	private static String ROLE_URL = "/role";
	
	private MockMvc mockMvc;
	
	@MockBean
	private RoleService roleService;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }
  
   @Test
   public void getRoles() throws Exception {
       when(roleService.getRoles()).thenReturn(new Roles());
       mockMvc.perform(
               get(ROLE_URL)
               .contentType(MediaType.APPLICATION_JSON)
               ).andExpect(status().isOk());
   }
	@Test
   public void addRoles() throws Exception {
	   when(roleService.addRole(any(Role.class))).thenReturn("hello world");
	   mockMvc.perform(
			   post(ROLE_URL)
			   .content("{\"id\":\"raki12\",\"roleName\":\"super admin\"}")
			   .contentType(MediaType.APPLICATION_JSON)
			   ).andExpect(status().isOk());
   }
	@Test
	public void deleteRoles() throws Exception {
		doNothing().when(roleService).deleteRole(anyString());
		 mockMvc.perform(
				   delete("/role/21")
				   .contentType(MediaType.APPLICATION_JSON)
				   ).andExpect(status().isOk());
	}
	@Test
	public void updateRole() throws Exception {
		when(roleService.updateRole(any(Role.class))).thenReturn("update");
		 mockMvc.perform(
				   post(ROLE_URL)
				   .content("{\"id\":\"raki12\",\"roleName\":\"moderator\"}")
				   .contentType(MediaType.APPLICATION_JSON)
				   ).andExpect(status().isOk());
	}
	@Test
	public void getRoleById() throws Exception {
		 when(roleService.getRoles()).thenReturn(new Roles());
	       mockMvc.perform(
	               get(ROLE_URL)
	               .content("{\"id}")
	               .contentType(MediaType.APPLICATION_JSON)
	               ).andExpect(status().isOk());
	}
	
}
