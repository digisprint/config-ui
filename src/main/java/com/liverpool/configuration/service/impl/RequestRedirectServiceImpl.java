package com.liverpool.configuration.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigList;
import com.liverpool.configuration.beans.ConfigMap;
import com.liverpool.configuration.beans.Configuration;
import com.liverpool.configuration.beans.MultiValuedConfigMap;
import com.liverpool.configuration.beans.ResponseData;
import com.liverpool.configuration.beans.StaticKeys;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.service.ConfigListService;
import com.liverpool.configuration.service.ConfigMapService;
import com.liverpool.configuration.service.MultiValuedConfigMapService;
import com.liverpool.configuration.service.RequestRedirectService;
import com.liverpool.configuration.service.StaticKeysService;

@Service
public class RequestRedirectServiceImpl implements RequestRedirectService{

	private StaticKeysService staticKeyService;
	
	private ConfigListService configListService;
	
	private ConfigMapService configMapService;
	
	private ConfigrationsProeprties properties;
	
	private MultiValuedConfigMapService multiValuedConfigService;
	
	public RequestRedirectServiceImpl(ConfigrationsProeprties properties, StaticKeysService staticKeyService, 
			ConfigListService configListService,ConfigMapService configMapService, MultiValuedConfigMapService multiValuedConfigService){
		this.staticKeyService = staticKeyService;
		this.configListService = configListService;
		this.configMapService = configMapService;
		this.multiValuedConfigService = multiValuedConfigService;
		this.properties = properties;
	}
	
	@Override
	public void redirectCreateRequest(String type, Configuration config) {
		if((properties.getStaticKeysTypeName()).equalsIgnoreCase(type)) {
			staticKeyService.createStaticKey(config.getStaticKeys());
		} else if ((this.properties.getConfigListTypeName()).equalsIgnoreCase(type)) {
			configListService.createConfigList(config.getConfigList());
		} else if ((this.properties.getConfigMapTypeName()).equalsIgnoreCase(type)) {
			configMapService.createConfigMap(config.getConfigMap());
		} else if ((this.properties.getMultiValuedConfigMapTypeName()).equalsIgnoreCase(type)) {
			multiValuedConfigService.createMultiValuedConfigMap(config.getMultiValuedConfigMap());
		}
	}

	@Override
	public void redirectUpdateRequest(String type, Configuration config) {
		if((properties.getStaticKeysTypeName()).equalsIgnoreCase(type)) {
			staticKeyService.updateStaticKey(config.getStaticKeys());
		} else if ((this.properties.getConfigListTypeName()).equalsIgnoreCase(type)) {
			configListService.updateConfigList(config.getConfigList());
		} else if ((this.properties.getConfigMapTypeName()).equalsIgnoreCase(type)) {
			configMapService.updateConfigMap(config.getConfigMap());
		} else if ((this.properties.getMultiValuedConfigMapTypeName()).equalsIgnoreCase(type)) {
			multiValuedConfigService.updateMultiValuedConfigMap(config.getMultiValuedConfigMap());
		}
	}

	@Override
	public ResponseData getAllConfigurations(String type) {
		ResponseData resp = new ResponseData();
		if((properties.getStaticKeysTypeName()).equalsIgnoreCase(type)) {
			List<StaticKeys> allStaticKeys = staticKeyService.getAllStaticKeys();
			resp.setBody(allStaticKeys);
		} else if ((this.properties.getConfigListTypeName()).equalsIgnoreCase(type)) {
			List<ConfigList> allConfigLists = configListService.getAllConfigLists();
			resp.setBody(allConfigLists);
		} else if ((this.properties.getConfigMapTypeName()).equalsIgnoreCase(type)) {
			List<ConfigMap> allConfigMaps = configMapService.getAllConfigMaps();
			resp.setBody(allConfigMaps);
		} else if ((this.properties.getMultiValuedConfigMapTypeName()).equalsIgnoreCase(type)) {
			List<MultiValuedConfigMap> allMultiValuedConfigMaps = multiValuedConfigService.getAllMultiValuedConfigMaps();
			resp.setBody(allMultiValuedConfigMaps);
		}
		return resp;
	}

	@Override
	public ResponseData getConfigurationByKey(String type, String key) {
		ResponseData resp = new ResponseData();
		if((properties.getStaticKeysTypeName()).equalsIgnoreCase(type)) {
			StaticKeys staticKey = staticKeyService.getStaticKeyByKey(key);
			resp.setBody(staticKey);
		} else if ((this.properties.getConfigListTypeName()).equalsIgnoreCase(type)) {
			ConfigList configList = configListService.getConfigListByKey(key);
			resp.setBody(configList);
		} else if ((this.properties.getConfigMapTypeName()).equalsIgnoreCase(type)) {
			ConfigMap configMap = configMapService.getConfigMapByKey(key);
			resp.setBody(configMap);
		} else if ((this.properties.getMultiValuedConfigMapTypeName()).equalsIgnoreCase(type)) {
			MultiValuedConfigMap multiValuedConfigMap = multiValuedConfigService.getMultiValuedConfigMapByKey(key);
			resp.setBody(multiValuedConfigMap);
		}
		return resp;
	}

	@Override
	public void deleteConfiguration(String type, String key) {
		
		if((properties.getStaticKeysTypeName()).equalsIgnoreCase(type)) {
			staticKeyService.deleteStaticKey(key);
		} else if ((this.properties.getConfigListTypeName()).equalsIgnoreCase(type)) {
			configListService.deleteConfigList(key);
		} else if ((this.properties.getConfigMapTypeName()).equalsIgnoreCase(type)) {
			configMapService.deleteConfigMap(key);
		} else if ((this.properties.getMultiValuedConfigMapTypeName()).equalsIgnoreCase(type)) {
			multiValuedConfigService.deleteMultiValuedConfigMap(key);
		}
	}

	@Override
	public ResponseData getConfigurationTypes() {
		List<String> configTypes = new ArrayList<String>();
		ResponseData resp = new ResponseData();
		configTypes.add(properties.getStaticKeysTypeName());
		configTypes.add(this.properties.getConfigListTypeName());
		configTypes.add(this.properties.getConfigMapTypeName());
		configTypes.add(this.properties.getMultiValuedConfigMapTypeName());
		resp.setBody(configTypes);
		return resp;
	}
}
