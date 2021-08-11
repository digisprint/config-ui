package com.liverpool.configuration.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ConfigDate;
import com.liverpool.configuration.constants.ConfigurationConstants;
import com.liverpool.configuration.repository.ConfigDateRepository;
import com.liverpool.configuration.service.ConfigDateService;

@Service
public class ConfigDateServiceImpl implements ConfigDateService{
	
	private ConfigDateRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${liverpool.configuration.configdate.collection}")
	private String configDateCollectionName;
	
	public ConfigDateServiceImpl(ConfigDateRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}
	

	@Override
	public List<ConfigDate> getAllConfigLists() {
		return repository.findAll();
	}

	@Override
	public void createConfigDate(ConfigDate config) {
		repository.insert(config);
	}

	@Override
	public void updateConfigDate(ConfigDate config) {
		repository.save(config);
		
	}

	@Override
	public ConfigDate getConfigDateByKey(String key) {
		return repository.findById(key).orElseThrow(RuntimeException::new);
	}

	@Override
	public void deleteConfigDate(String key) {
		Document filter = new Document(ConfigurationConstants.ID,key);
		mongoTemplate.getCollection(configDateCollectionName).deleteOne(filter);
		
	}

}
