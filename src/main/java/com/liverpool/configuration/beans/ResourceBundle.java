package com.liverpool.configuration.beans;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "resource_bundle")
@Setter
@Getter
@ToString
public class ResourceBundle {
	
	@Id
	private String key;
	private String value;
	private Map<String, String> siteValues;

}
