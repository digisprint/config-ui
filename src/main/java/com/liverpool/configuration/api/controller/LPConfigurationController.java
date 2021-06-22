package com.liverpool.configuration.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.api.bean.User;
import com.liverpool.configuration.api.modal.Configuration;
import com.liverpool.configuration.api.repository.LPConfigurationRepository;

@RestController
public class LPConfigurationController {
	
	@Autowired
	private LPConfigurationRepository repository;
	
	@PostMapping("/createConfiguration")
	public String createConfiguration(@RequestBody Configuration config) {
		repository.save(config);
		return "Values configured successfully " +config.getKey();
	}
	
	@GetMapping("/listAllConfigurations")
	public List<Configuration> getConfigurations(){
		return repository.findAll();
	}
	
	@GetMapping("/getConfigurationByKey")
	public Optional<Configuration> getConfigurationByKey(@RequestParam String key){
		return repository.findById(key);
	}
	
	@GetMapping("/deleteConfigurationByKey")
	public String deleteConfiguration(@RequestParam String key){
		repository.deleteById(key);
		return "Configuration has been deleted";
	}
	
}
