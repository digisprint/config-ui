package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.configuration.beans.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

}
