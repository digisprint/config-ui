package com.liverpool.configuration.service;

import com.liverpool.configuration.beans.Configuration;
import com.liverpool.configuration.beans.ResponseData;

public interface RequestRedirectService {
	
	public void redirectCreateRequest(String typeName, Configuration config);

	public void redirectUpdateRequest(String type, Configuration config);
	
	public ResponseData getAllConfigurations(String type);

	public ResponseData getConfigurationByKey(String type, String key);

	public void deleteConfiguration(String type, String key);
	
	public ResponseData getConfigurationTypes(String token);
	
}
