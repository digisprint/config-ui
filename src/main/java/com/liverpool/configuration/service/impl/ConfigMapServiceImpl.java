package com.liverpool.configuration.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigMap;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.ConfigMapRepository;
import com.liverpool.configuration.service.ConfigMapService;

import lombok.NonNull;

@Service
public class ConfigMapServiceImpl implements ConfigMapService{
	
	private ConfigMapRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${liverpool.configuration.configmap.collection}")
	private String configMapCollectionName;
	
	public ConfigMapServiceImpl(ConfigMapRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}
	
	@Override
	@CacheEvict(value = "configMapCache", allEntries = true)
	public void createConfigMap(@NonNull ConfigMap config) {
		repository.insert(config);
	}

	@Override
	@CacheEvict(value = "configMapCache", allEntries = true)
	public void updateConfigMap(@NonNull ConfigMap config) {
		repository.save(config);
	}

	@Override
	@Cacheable(value = "configMapCache")
	public List<ConfigMap> getAllConfigMaps() {
		return repository.findAll();
	}

	@Override
	@Cacheable(value = "configMapCache", key = "#key")
	public ConfigMap getConfigMapByKey(@NonNull String key) {
		return repository.findById(key).orElseThrow(RuntimeException::new);
	}

	@Override
	@CacheEvict(value = "configMapCache", allEntries = true)
	public void deleteConfigMap(@NonNull String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(configMapCollectionName).deleteOne(filter);
	}

}
