package com.liverpool.configuration.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigList;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.ConfigListRepository;
import com.liverpool.configuration.service.ConfigListService;

import lombok.NonNull;

@Service
public class ConfigListServiceImpl implements ConfigListService{
	
	private ConfigListRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${liverpool.configuration.configlist.collection}")
	private String configListCollectionName;
	
	public ConfigListServiceImpl(ConfigListRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}
	

	@Override
	@CacheEvict(value = "configListCache", allEntries = true)
	public void createConfigList(@NonNull ConfigList config) {
		repository.insert(config);
	}

	@Override
	@CacheEvict(value = "configListCache", allEntries = true)
	public void updateConfigList(@NonNull ConfigList config) {
		repository.save(config);
	}

	@Override
	@Cacheable(value = "configListCache")
	public List<ConfigList> getAllConfigLists() {
		return repository.findAll();
	}

	@Override
	@Cacheable(value = "configListCache", key = "#key")
	public ConfigList getConfigListByKey(@NonNull String key) {
		return repository.findById(key).orElseThrow(RuntimeException::new);
	}

	@Override
	@CacheEvict(value = "configListCache", allEntries = true)
	public void deleteConfigList(@NonNull String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(configListCollectionName).deleteOne(filter);
	}

}
