package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.ConfigList;

public interface ConfigListService {
	
	//config list CRUD operations
	public void createConfigList(ConfigList configList);
	public void updateConfigList(ConfigList configList);
	public List<ConfigList> getAllConfigLists();
	public ConfigList getConfigListByKey(String key);
	public void deleteConfigList(String key);
	
}
