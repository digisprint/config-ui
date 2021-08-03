package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.List;

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

@Document(collection = "config_list")
@Setter
@Getter
@ToString
@BeanConfiguration(name="List Configurations",url_path="configList", accessPrivilegeName="configlist")
@Component
public class ConfigList implements Serializable{
	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String", order=1, objectKey = true)
	private String key;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "List", order=2)
	private List<String> value;
}
