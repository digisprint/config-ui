package com.liverpool.configuration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.liverpool.configuration.beans.EmailTemplate;

public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, String> {

}
