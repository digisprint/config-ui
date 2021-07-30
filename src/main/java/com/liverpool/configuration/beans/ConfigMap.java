package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.annotations.DisplayProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "config_map")
@Setter
@Getter
@ToString
@BeanConfiguration(name="Map Configurations",url_path="configMap", accessPrivilegeName="configmap")
@Component
public class ConfigMap implements Serializable{
	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String")
	private String key;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "Map")
	private Map<String, String> value;


}
