package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.ConfigList;

@Repository
public interface ConfigListRepository extends MongoRepository<ConfigList, String>{

}
