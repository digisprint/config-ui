package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.MultiValuedConfigMap;

public interface MultiValuedConfigMapService {
	
	//multi valued config map CRUD operations
	public void createMultiValuedConfigMap(MultiValuedConfigMap configMap);
	public void updateMultiValuedConfigMap(MultiValuedConfigMap configMap);
	public List<MultiValuedConfigMap> getAllMultiValuedConfigMaps();
	public MultiValuedConfigMap getMultiValuedConfigMapByKey(String key);
	public void deleteMultiValuedConfigMap(String key);
	
}
