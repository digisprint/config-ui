package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.ResourceBundle;

public interface ResourceBundleService {
	
	String createResourceBundle(ResourceBundle config);
	String updateResourceBundle(ResourceBundle config);
	List<ResourceBundle> getResourceBundles();
	ResourceBundle getResourceBundleByKey(String key);
	String deleteResourceBundle(String key);
	
}
