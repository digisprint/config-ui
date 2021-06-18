package com.liverpool.configuration.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.configuration.beans.ResourceBundle;
import com.liverpool.configuration.service.ResourceBundleService;

@RestController
public class ResourceBundleController {
	
	private ResourceBundleService resourceService;
	
	public ResourceBundleController(ResourceBundleService resourceService){
		this.resourceService = resourceService;
	}
	
	@PostMapping("/resourceBundle")
	public String createResourceBundle(@RequestBody ResourceBundle config) {
		return resourceService.createResourceBundle(config);
	}
	
	@PutMapping("/resourceBundle")
	public String updatedResourceBundle(@RequestBody ResourceBundle config) {
		return resourceService.updateResourceBundle(config);
	}
	
	@GetMapping("/resourceBundle")
	public List<ResourceBundle> getResourceBundles(){
		return resourceService.getResourceBundles();
	}
	
	@GetMapping("/resourceBundle/{key}")
	public ResourceBundle getResourceBundleByKey(@PathVariable String key){
		return resourceService.getResourceBundleByKey(key);
	}
	
	@DeleteMapping("/resourceBundle/{key}")
	public String deleteResourceBundleByKey(@PathVariable String key){
		return resourceService.deleteResourceBundle(key);
	}

	
}
