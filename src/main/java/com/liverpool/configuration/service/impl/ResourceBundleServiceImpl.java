package com.liverpool.configuration.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.liverpool.configuration.beans.ResourceBundle;
import com.liverpool.configuration.constants.ResourceBundleConstants;
import com.liverpool.configuration.repository.ResourceBundleRepository;
import com.liverpool.configuration.service.ResourceBundleService;
import com.mongodb.client.model.ReplaceOptions;

@Service
public class ResourceBundleServiceImpl implements ResourceBundleService{
	
	private ResourceBundleRepository repository;
	
	private MongoTemplate mongoTemplate;
	
	@Value("${spring.data.mongodb.collection.bundle}")
	private String bundleCollectionName;
	
	public ResourceBundleServiceImpl(ResourceBundleRepository repository, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.mongoTemplate=mongoTemplate;
	}

	@Override
	public String createResourceBundle(ResourceBundle config) {
		Document doc = new Document(ResourceBundleConstants.ID,config.getKey())
				.append(ResourceBundleConstants.VALUE, config.getValue())
				.append(ResourceBundleConstants.SITE_VALUES, config.getSiteValues());
		mongoTemplate.getCollection(bundleCollectionName).insertOne(doc);
		return "Values configured successfully " +config.getKey();
	}

	@Override
	public String updateResourceBundle(ResourceBundle config) {
		Document doc = new Document(ResourceBundleConstants.ID,config.getKey())
				.append(ResourceBundleConstants.VALUE, config.getValue())
				.append(ResourceBundleConstants.SITE_VALUES, config.getSiteValues());
		Document filter = new Document(ResourceBundleConstants.ID,config.getKey());
		ReplaceOptions options = new ReplaceOptions().upsert(true);
		mongoTemplate.getCollection(bundleCollectionName).replaceOne(filter, doc, options);
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
		Document filter = new Document(ResourceBundleConstants.ID,key);
		mongoTemplate.getCollection(bundleCollectionName).deleteOne(filter);
		return "Resource bundle has been deleted " +key;
	}

	

}
