package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.ConfigDate;

public interface ConfigDateService {
	
	//config list CRUD operations
	public void createConfigDate(ConfigDate configDate);
	public void updateConfigDate(ConfigDate configDate);
	public List<ConfigDate> getAllConfigLists();
	public ConfigDate getConfigDateByKey(String key);
	public void deleteConfigDate(String key);
	
}
