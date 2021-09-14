package com.liverpool.configuration.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.annotations.DisplayProperty;
import com.liverpool.configuration.beans.ConfigurationTypes;
import com.liverpool.configuration.beans.ResponseData;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.RequestRedirectService;



@Service
public class RequestRedirectServiceImpl implements RequestRedirectService{

	
	private ConfigrationsProeprties properties;
	
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationContext context;
	
	public static final String TOKEN_PREFIX = "Bearer ";

	
	
	public RequestRedirectServiceImpl(ConfigrationsProeprties properties,UserRepository userRepo){
		
		this.properties = properties;
		this.userRepo = userRepo;
		
	}

	@Override
	public ResponseData getConfigurationTypes(String token) {
		
		List<ConfigurationTypes> configTypes = new ArrayList<ConfigurationTypes>();
		ResponseData resp = new ResponseData();
		Map<String, Object> beans = context.getBeansWithAnnotation(BeanConfiguration.class);
		String usernameFromToken = getUsernameFromToken(token);
		User user = userRepo.findByUserName(usernameFromToken);
		Map<String, String> accessPrivileges = user.getAccessPrivileges();
		beans.forEach((key, value) -> {
			ConfigurationTypes configType = new ConfigurationTypes();
			Map<String, String> configMap = new LinkedHashMap<String, String>();
			Field[] fields = value.getClass().getDeclaredFields();
			Arrays.sort(fields, new Comparator<Field>() {
	            @Override
	            public int compare(Field o1, Field o2) {
	            	DisplayProperty or1 = o1.getAnnotation(DisplayProperty.class);
	            	DisplayProperty or2 = o2.getAnnotation(DisplayProperty.class);
	                if (or1 != null && or2 != null) {
	                    return or1.order() - or2.order();
	                } else
	                if (or1 != null && or2 == null) {
	                    return -1;
	                } else
	                if (or1 == null && or2 != null) {
	                    return 1;
	                }
	                return o1.getName().compareTo(o2.getName());
	            }
	        });
			
			for (Field f : fields) {
				DisplayProperty annotation = f.getAnnotation(DisplayProperty.class);
				if(annotation != null) {
					if(annotation.display()) {
						configType.setDisplayProperty(f.getName());
					}
					if(annotation.objectKey()) {
						configType.setObjectKey(f.getName());
					}
					configMap.put(f.getName(),annotation.uiPropType());
				}
	        }
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
			if(StringUtils.isBlank(configType.getAccess())) {
				configType.setAccess("view");
			}
			if(!"none".equalsIgnoreCase(configType.getAccess())) {
				configTypes.add(configType);
			}
		});
		
		resp.setBody(configTypes);
		return resp;
	}
	
	public String getUsernameFromToken(String token) {
		return JWT.require(Algorithm.HMAC512(properties.getSecretKey().getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
	}
}
