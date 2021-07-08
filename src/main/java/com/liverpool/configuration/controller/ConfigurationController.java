package com.liverpool.configuration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationController extends BaseController {

	private RequestRedirectService redirectService;

	public ConfigurationController(RequestRedirectService redirectService) {
		this.redirectService = redirectService;
	}

	@Operation(summary = "This service used to create configuration")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@PostMapping("/config/{type}")
	public ResponseEntity<ResponseData> createConfiguration(@PathVariable String type,
			@RequestBody Configuration config) {
		redirectService.redirectCreateRequest(type, config);
		return success(config, HttpStatus.OK, "Configuration created of type::::" + type);
	}

	@Operation(summary = "This service used to update configuration")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@PutMapping("/config/{type}")
	public ResponseEntity<ResponseData> updateConfiguration(@PathVariable String type,
			@RequestBody Configuration config) {
		redirectService.redirectUpdateRequest(type, config);
		return success(config, HttpStatus.OK, "Configuration updated!!");
	}

	@Operation(summary = "This service used to get all configurations")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Configurations Response", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@GetMapping("/config/{type}")
	public ResponseEntity<ResponseData> getAllConfigurations(@PathVariable String type) {

		log.info("retrieving configuration for " + type);
		ResponseData resp = redirectService.getAllConfigurations(type);
		return success(resp.getBody(), HttpStatus.OK, "Displaying all configurations of " + type);
	}

	@Operation(summary = "This service used to get configuration by key")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Configuration Response", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@GetMapping("/config/{type}/{key}")
	public ResponseEntity<ResponseData> getConfigurationByKey(@PathVariable String type, @PathVariable String key) {
		ResponseData resp = redirectService.getConfigurationByKey(type, key);
		return success(resp.getBody(), HttpStatus.OK, "Displaying configuration for key " + key);
	}

	@Operation(summary = "This service used to delete configuration")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@DeleteMapping("/config/{type}/{key}")
	public ResponseEntity<ResponseData> deleteConfigurationByKey(@PathVariable String type, @PathVariable String key) {
		redirectService.deleteConfiguration(type, key);
		return success(key, HttpStatus.OK, "Deleted configuration for key " + key);
	}

	@Operation(summary = "This service used to get the available configuration types")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data not found", content = @Content) })
	@GetMapping("/config")
	public ResponseEntity<ResponseData> getConfigurationTypes() {
		ResponseData resp = redirectService.getConfigurationTypes();
		return success(resp.getBody(), HttpStatus.OK, "Getting the available configuration types");
	}

}
