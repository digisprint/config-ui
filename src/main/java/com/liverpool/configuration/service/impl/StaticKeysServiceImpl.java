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

import com.liverpool.configuration.beans.StaticKeys;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.StaticKeysRepository;
import com.liverpool.configuration.service.StaticKeysService;
import com.mongodb.client.model.ReplaceOptions;

import lombok.NonNull;

@Service
public class StaticKeysServiceImpl implements StaticKeysService{
	
	private StaticKeysRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${liverpool.configuration.statickeys.collection}")
	private String staticKeysCollectionName;
	
	public StaticKeysServiceImpl(StaticKeysRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "staticKeysCache", allEntries = true), }, 
	 put = { @CachePut(value = "staticKeysCache", key = "#config.key") })
	public void createStaticKey(@NonNull StaticKeys config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue())
				.append(ConfigurationConstants.SITE_VALUES, config.getSiteValues());
		mongoTemplate.getCollection(staticKeysCollectionName).insertOne(doc);
	}

	@Override
	@CachePut(value = "staticKeysCache", key = "#config.key")
	public void updateStaticKey(@NonNull StaticKeys config) {
		Document doc = new Document(ConfigurationConstants.ID,config.getKey())
				.append(ConfigurationConstants.VALUE, config.getValue())
				.append(ConfigurationConstants.SITE_VALUES, config.getSiteValues());
		Document filter = new Document(ConfigurationConstants.ID,config.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection(staticKeysCollectionName).replaceOne(filter, doc, options);
	}
	
	@Override
	@Cacheable(value = "staticKeysCache")
	public List<StaticKeys> getAllStaticKeys() {
		return repository.findAll();		
	}

	@Override
	@Cacheable(value = "staticKeysCache", key = "#key")
	public StaticKeys getStaticKeyByKey(@NonNull String key) {
		return repository.findById(key).orElseThrow(RuntimeException::new);
	}

	@Override
	@CacheEvict(value = "staticKeysCache", key = "#key")
	public void deleteStaticKey(@NonNull String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(staticKeysCollectionName).deleteOne(filter);
	}

}