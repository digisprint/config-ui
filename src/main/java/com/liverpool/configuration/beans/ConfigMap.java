package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "config_map")
@Setter
@Getter
@ToString
@DisplayName(name="Map Configurations")
@Component
public class ConfigMap implements Serializable{
	@Id
	@NotBlank(message = "Key should not be empty")
	private String key;
	
	@NotEmpty(message = "Value should not be empty")
	private Map<String, String> value;


}
