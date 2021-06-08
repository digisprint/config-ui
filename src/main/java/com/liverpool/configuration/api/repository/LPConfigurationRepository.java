package com.liverpool.configuration.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.liverpool.configuration.api.modal.Configuration;

public interface LPConfigurationRepository extends MongoRepository<Configuration, String>{

}
