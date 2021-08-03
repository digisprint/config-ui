package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.List;
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

@Document(collection = "multi_valued_map")
@Setter
@Getter
@ToString
@BeanConfiguration(name="Multi Valued Configuration",url_path="multiValuedConfigMap", accessPrivilegeName="multivaluedconfigmap")
@Component
public class MultiValuedConfigMap implements Serializable{
	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String", order=1)
	private String key;
	@DisplayProperty(uiPropType = "String", order=2)
	private String type;
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "Map", order=3)
	private Map<@NotBlank String, List<@NotBlank String>> value;


} 
