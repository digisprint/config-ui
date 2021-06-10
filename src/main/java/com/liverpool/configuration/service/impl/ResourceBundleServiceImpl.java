package com.liverpool.configuration.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ResourceBundle;
import com.liverpool.configuration.repository.ResourceBundleRepository;
import com.liverpool.configuration.service.ResourceBundleService;
import com.mongodb.client.model.ReplaceOptions;

@Service
public class ResourceBundleServiceImpl implements ResourceBundleService{
	
	private ResourceBundleRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	public ResourceBundleServiceImpl(ResourceBundleRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}

	@Override
	public String createResourceBundle(ResourceBundle config) {
		Document doc = new Document("_id",config.getKey())
				.append("value", config.getValue());
		mongoTemplate.getCollection("resource_bundle").insertOne(doc);
		return "Values configured successfully " +config.getKey();
	}

	@Override
	public String updateResourceBundle(ResourceBundle config) {
		Document doc = new Document("_id",config.getKey())
				.append("value", config.getValue());
		Document filter = new Document("_id",config.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection("resource_bundle").replaceOne(filter, doc, options);
		return "Values updated successfully " +config.getKey();
	}
	
	@Override
	public List<ResourceBundle> getResourceBundles() {
		return repository.findAll();		
	}

	@Override
	public ResourceBundle getResourceBundleByKey(String key) {
		return Optional.ofNullable(repository.findById(key)).orElse(null).get();
	}

	@Override
	public String deleteResourceBundle(String key) {
		Document filter = new Document("_id",key);
		mongoTemplate.getCollection("resource_bundle").deleteOne(filter);
		return "Resource bundle has been deleted " +key;
	}

	

}
