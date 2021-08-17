package com.liverpool.configuration.service;

import com.liverpool.configuration.beans.ResponseData;

public interface RequestRedirectService {
	
	public ResponseData getConfigurationTypes(String token);
	
}
