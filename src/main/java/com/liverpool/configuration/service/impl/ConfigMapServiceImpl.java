package com.liverpool.configuration.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigMap;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.ConfigMapRepository;
import com.liverpool.configuration.service.ConfigMapService;
import com.mongodb.client.model.ReplaceOptions;

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
	@Caching(evict = { @CacheEvict(value = "configMapCache", allEntries = true), }, 
	 put = { @CachePut(value = "configMapCache", key = "#config.key") })
	public void createConfigMap(@NonNull ConfigMap config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue());
		mongoTemplate.getCollection(configMapCollectionName).insertOne(doc);
	}

	@Override
	@CachePut(value = "configMapCache", key = "#config.key")
	public void updateConfigMap(@NonNull ConfigMap config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue());
		Document filter = new Document(ConfigurationConstants.ID,config.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection(configMapCollectionName).replaceOne(filter, doc, options);
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
	@CacheEvict(value = "configMapCache", key = "#key")
	public void deleteConfigMap(@NonNull String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(configMapCollectionName).deleteOne(filter);
	}

}
