package com.liverpool.configuration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.beans.Configuration;
import com.liverpool.configuration.beans.ResponseData;
import com.liverpool.configuration.service.RequestRedirectService;

@RestController
public class ConfigurationController extends BaseController {
	
	private RequestRedirectService redirectService;
	
	public ConfigurationController(RequestRedirectService redirectService){
		this.redirectService = redirectService;
	}
	
	
	@PostMapping("/config/{type}")
	public ResponseEntity<ResponseData> createConfiguration(@PathVariable String type, @RequestBody Configuration config) {
		redirectService.redirectCreateRequest(type, config);
		return success(config, HttpStatus.OK, "Configuration created of type "+type);
	}
	
	@PutMapping("/config/{type}")
	public ResponseEntity<ResponseData> updateConfiguration(@PathVariable String type, @RequestBody Configuration config) {
		redirectService.redirectUpdateRequest(type, config);
		return success(config, HttpStatus.OK, "Configuration updated!!");
	}
	
	@GetMapping("/config/{type}")
	public ResponseEntity<ResponseData> getAllConfigurations(@PathVariable String type){
		ResponseData resp = redirectService.getAllConfigurations(type);
		return success(resp.getBody(), HttpStatus.OK, "Displaying all configurations of "+type);
	}
	
	@GetMapping("/config/{type}/{key}")
	public ResponseEntity<ResponseData> getConfigurationByKey(@PathVariable String type, @PathVariable String key){
		ResponseData resp = redirectService.getConfigurationByKey(type, key);
		return success(resp.getBody(), HttpStatus.OK, "Displaying configuration for key "+key);
	}
	
	@DeleteMapping("/config/{type}/{key}")
	public ResponseEntity<ResponseData> deleteConfigurationByKey(@PathVariable String type, @PathVariable String key){
		redirectService.deleteConfiguration(type, key);
		return success(key, HttpStatus.OK, "Deleted configuration for key "+key);
	}
	
}
