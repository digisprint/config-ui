package com.liverpool.configuration.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigList;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.ConfigListRepository;
import com.liverpool.configuration.service.ConfigListService;
import com.mongodb.client.model.ReplaceOptions;

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
	@Caching(evict = { @CacheEvict(value = "configListCache", allEntries = true), }, 
			 put = { @CachePut(value = "configListCache", key = "#config.key") })
	public void createConfigList(@NonNull ConfigList config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue());
		mongoTemplate.getCollection(configListCollectionName).insertOne(doc);
	}

	@Override
	@CachePut(value = "configListCache", key = "#config.key")
	public void updateConfigList(@NonNull ConfigList config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue());
		Document filter = new Document(ConfigurationConstants.ID,config.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection(configListCollectionName).replaceOne(filter, doc, options);
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
	@CacheEvict(value = "configListCache", key = "#key")
	public void deleteConfigList(@NonNull String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(configListCollectionName).deleteOne(filter);
	}

}
