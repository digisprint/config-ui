package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.ResourceBundle;

@Repository
public interface ResourceBundleRepository extends MongoRepository<ResourceBundle, String>{

}
