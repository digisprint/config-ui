package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.ConfigMap;

@Repository
public interface ConfigMapRepository extends MongoRepository<ConfigMap, String>{

}
