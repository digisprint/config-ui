package com.liverpool.configuration.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.liverpool.configuration.beans.StaticKeys;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.service.ConfigListService;
import com.liverpool.configuration.service.ConfigMapService;
import com.liverpool.configuration.service.RequestRedirectService;
import com.liverpool.configuration.service.StaticKeysService;

@WebMvcTest(RequestRedirectServiceImpl.class)

public class RequestRedirectServiceImplTest {
	
	@MockBean
	private StaticKeysService staticKeyService;
	
	@MockBean
	private ConfigListService configListService;
	
	@MockBean
	private ConfigMapService configMapService;
	
	@MockBean
	private ConfigrationsProeprties properies;
	
	private RequestRedirectService service;
	
	@BeforeEach
	public void setUp() {
		service = new RequestRedirectServiceImpl(properies,staticKeyService, configListService, configMapService);
		when(properies.getConfigListTypeName()).thenReturn("configList");
		when(properies.getStaticKeysTypeName()).thenReturn("staticKeys");
	}
	
	@Test
	public void getAllConfigurationsStaticKeys() {

		when(staticKeyService.getAllStaticKeys()).thenReturn(new ArrayList<StaticKeys>());
		assertThat(service.getAllConfigurations("staticKeys")).isNotNull();
		
	}
}
