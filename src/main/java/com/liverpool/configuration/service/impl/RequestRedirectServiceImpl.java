package com.liverpool.configuration.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.annotations.DisplayProperty;
import com.liverpool.configuration.beans.ConfigList;
import com.liverpool.configuration.beans.ConfigMap;
import com.liverpool.configuration.beans.Configuration;
import com.liverpool.configuration.beans.ConfigurationTypes;
import com.liverpool.configuration.beans.MultiValuedConfigMap;
import com.liverpool.configuration.beans.ResponseData;
import com.liverpool.configuration.beans.StaticKeys;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.config.JwtTokenUtil;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.UserRepository;
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
	
	private JwtTokenUtil jwtUtil;
	
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationContext context;
	
	public RequestRedirectServiceImpl(ConfigrationsProeprties properties, StaticKeysService staticKeyService, 
			ConfigListService configListService,ConfigMapService configMapService, MultiValuedConfigMapService multiValuedConfigService,
			JwtTokenUtil jwtUtil, UserRepository userRepo){
		this.staticKeyService = staticKeyService;
		this.configListService = configListService;
		this.configMapService = configMapService;
		this.multiValuedConfigService = multiValuedConfigService;
		this.properties = properties;
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
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
	public ResponseData getConfigurationTypes(String token) {
		
		List<ConfigurationTypes> configTypes = new ArrayList<ConfigurationTypes>();
		ResponseData resp = new ResponseData();
		Map<String, Object> beans = context.getBeansWithAnnotation(BeanConfiguration.class);
		String usernameFromToken = jwtUtil.getUsernameFromToken(token);
		User user = userRepo.findByUserName(usernameFromToken);
		Map<String, String> accessPrivileges = user.getAccessPrivileges();
		beans.forEach((key, value) -> {
			ConfigurationTypes configType = new ConfigurationTypes();
			Map<String, String> configMap = new TreeMap<String, String>();
			Field[] fields = value.getClass().getDeclaredFields();
			Arrays.stream(fields).forEach(field -> {
				DisplayProperty annotation = field.getAnnotation(DisplayProperty.class);
				if(annotation != null) {
					if(annotation.display()) {
						configType.setDisplayProperty(field.getName());
					}
					configMap.put(field.getName(),annotation.uiPropType());
				}
			});
			String accessPrivilegeName = value.getClass().getAnnotation(BeanConfiguration.class).accessPrivilegeName();
			configType.setBeanName(accessPrivilegeName);
			configType.setDisplayName(value.getClass().getAnnotation(BeanConfiguration.class).name());
			configType.setUrlPath(value.getClass().getAnnotation(BeanConfiguration.class).url_path());
			configType.setProperties(configMap);
			if(accessPrivileges != null && !accessPrivileges.isEmpty()) {
				accessPrivileges.forEach((accessKey, accessValue) -> {
					if(accessPrivilegeName.equalsIgnoreCase(accessKey)) {
						configType.setAccess(accessValue);
					}
				});
			}
			if(configType.getAccess() != null && !"none".equalsIgnoreCase(configType.getAccess())) {
				configTypes.add(configType);
			}
		});
		
		
		resp.setBody(configTypes);
		return resp;
	}
}
