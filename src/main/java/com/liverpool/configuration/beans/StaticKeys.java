package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "static_keys")
@Setter
@Getter
@ToString
@BeanConfiguration(name="Static Keys",url_path="staticKeys")
@Component
public class StaticKeys implements Serializable{
	
	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String")
	private String key;
	@NotBlank(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String")
	private String value;
	@DisplayProperty(uiPropType = "Map")
	private Map<String, String> siteValues;

}
