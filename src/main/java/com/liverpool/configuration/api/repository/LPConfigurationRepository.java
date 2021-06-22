package com.liverpool.configuration.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.api.bean.User;
import com.liverpool.configuration.api.modal.Configuration;

@Repository
public interface LPConfigurationRepository extends MongoRepository<Configuration, String>{

}
