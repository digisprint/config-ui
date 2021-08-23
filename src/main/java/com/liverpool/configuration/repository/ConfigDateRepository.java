package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.ConfigDate;

@Repository
public interface ConfigDateRepository extends MongoRepository<ConfigDate, String>{

}