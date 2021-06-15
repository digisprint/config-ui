package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.ConfigMap;

public interface ConfigMapService {
	
	//config map CRUD operations
	public void createConfigMap(ConfigMap configMap);
	public void updateConfigMap(ConfigMap configMap);
	public List<ConfigMap> getAllConfigMaps();
	public ConfigMap getConfigMapByKey(String key);
	public void deleteConfigMap(String key);
	
}
