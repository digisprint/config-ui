package com.liverpool.configuration.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.MultiValuedConfigMap;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.MultiValuedConfigMapRepository;
import com.liverpool.configuration.service.MultiValuedConfigMapService;
import com.mongodb.client.model.ReplaceOptions;

@Service
public class MultiValuedConfigMapServiceImpl implements MultiValuedConfigMapService{
	
	private MultiValuedConfigMapRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${liverpool.configuration.multivalued.configmap.collection}")
	private String multiValuedConfigMapCollectionName;
	
	public MultiValuedConfigMapServiceImpl(MultiValuedConfigMapRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}
	
	@Override
	@CacheEvict(value = "multiValuedConfigMapCache", allEntries = true)
	public void createMultiValuedConfigMap(MultiValuedConfigMap configMap) {
		repository.insert(configMap);
	}

	@Override
	@CacheEvict(value = "multiValuedConfigMapCache", allEntries = true)
	public void updateMultiValuedConfigMap(MultiValuedConfigMap configMap) {
		Document doc = new Document(ConfigurationConstants.ID,configMap.getKey())
				.append(ConfigurationConstants.VALUE, configMap.getValue());
		Document filter = new Document(ConfigurationConstants.ID,configMap.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection(multiValuedConfigMapCollectionName).replaceOne(filter, doc, options);
		
	}

	@Override
	@Cacheable(value = "multiValuedConfigMapCache")
	public List<MultiValuedConfigMap> getAllMultiValuedConfigMaps() {
		return repository.findAll();
	}

	@Override
	@Cacheable(value = "multiValuedConfigMapCache", key = "#key")
	public MultiValuedConfigMap getMultiValuedConfigMapByKey(String key) {
		return repository.findById(key).orElseThrow(RuntimeException::new);
	}

	@Override
	@CacheEvict(value = "multiValuedConfigMapCache", allEntries = true)
	public void deleteMultiValuedConfigMap(String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(multiValuedConfigMapCollectionName).deleteOne(filter);
	}

}
