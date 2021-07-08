package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.MultiValuedConfigMap;

@Repository
public interface MultiValuedConfigMapRepository extends MongoRepository<MultiValuedConfigMap, String>{

}
