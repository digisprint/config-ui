package com.liverpool.configuration.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.beans.ConfigList;
import com.liverpool.configuration.beans.ConfigMap;
import com.liverpool.configuration.beans.ResponseData;
import com.liverpool.configuration.beans.StaticKeys;
import com.liverpool.configuration.controller.ConfigurationController;
import com.liverpool.configuration.service.RequestRedirectService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(ConfigurationController.class)
@ActiveProfiles("junit")
@Slf4j
public class ConfigurationControllerTest {

	@MockBean
	private ResponseData response;
	
	@MockBean
	private RequestRedirectService service;
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 private ObjectMapper objectMapper;
	 
	 @BeforeEach
	 public void setUp() {
	 objectMapper = new ObjectMapper();
	 }
	
	@Test
	public void getAllStaticKeysConfigurations() throws Exception {
		
		StaticKeys keys = new StaticKeys();
		
		when(service.getAllConfigurations(any(String.class))).thenReturn(new ResponseData());
		mockMvc.perform(get("/config/staticKeys").content(objectMapper.writeValueAsString(keys)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void getAllConfigListConfigurations() throws Exception {
		
		ConfigList list = new ConfigList();
		
		when(service.getAllConfigurations(any(String.class))).thenReturn(new ResponseData());
		mockMvc.perform(get("/config/configlist").content(objectMapper.writeValueAsString(list)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void getAllConfigMapConfigurations() throws Exception {
		
		ConfigMap configmap = new ConfigMap();
		
		when(service.getAllConfigurations(any(String.class))).thenReturn(new ResponseData());
		mockMvc.perform(get("/config/configlist").content(objectMapper.writeValueAsString(configmap)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
