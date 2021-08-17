package com.liverpool.configuration.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.repository.ConfigDateRepository;
import com.liverpool.configuration.repository.ConfigListRepository;
import com.liverpool.configuration.repository.ConfigMapRepository;
import com.liverpool.configuration.repository.EmailTemplateRepository;
import com.liverpool.configuration.repository.MultiValuedConfigMapRepository;
import com.liverpool.configuration.repository.StaticKeysRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenericRepositoryService {

	@Autowired
	private ConfigDateRepository configDateRepository;
	
	@Autowired
	private ConfigMapRepository configMapRepository;
	
	@Autowired
	private ConfigListRepository configListRepository;
	
	@Autowired
	private StaticKeysRepository staticKeysRepository;
	
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	
	@Autowired
	private MultiValuedConfigMapRepository multiValuedConfigMapRepository;

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ApplicationContext context;
	
	private MongoRepository getRepository(String repoType)
	{
		switch(repoType.toLowerCase())
		{
			case "configdate":
				return configDateRepository;
			case "configmap":
				return configMapRepository;
			case "configlist":
				return configListRepository;
			case "statickeys":
				return staticKeysRepository;
			case "emailtemplate":
				return emailTemplateRepository;
			case "multivalue":
				return multiValuedConfigMapRepository;
			default:
				return null;
		}
	}
	
	public <T> List<T> findByAll( String repoName )
	{
		return (List<T>) getRepository(repoName).findAll();
	}
	
	public <T> T findById(String repoName, String Id )
	{
		return (T) getRepository(repoName).findById(Id).orElse(null);
	}
	
	public Object create(String repoName, String payload) {
		return getRepository(repoName).insert(parsePayload(getObjectType(repoName), payload));
	}

	public  Object update(String repoName,  String payload) {
		return getRepository(repoName).save(parsePayload(getObjectType(repoName), payload));
	}
	
	public <T> void deleteById(String repoName , String Id) {
		getRepository(repoName).deleteById(Id);;
	}
	
	private <T> T parsePayload(Class<T> responseType, @NonNull String payload)
	{
		try {
			return objectMapper.readValue(payload, responseType);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
	private Class<? extends Object> getObjectType(String repoName) {
		Map<String, Object> beans = context.getBeansWithAnnotation(BeanConfiguration.class);
	
		Optional<Object> object = beans.entrySet().stream()
		  .filter(e -> e.getKey().equalsIgnoreCase(repoName))
		  .map(Map.Entry::getValue)
		  .findFirst();
		  
		return object.get().getClass();
	}
}
