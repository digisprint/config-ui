package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.StaticKeys;

@Repository
public interface StaticKeysRepository extends MongoRepository<StaticKeys, String>{

}
