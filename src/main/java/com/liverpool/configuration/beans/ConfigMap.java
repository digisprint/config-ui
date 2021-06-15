package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "config_map")
@Setter
@Getter
@ToString
public class ConfigMap implements Serializable{
	@Id
	private String key;
	private Map<String, String> value;


}
