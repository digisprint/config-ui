package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "multi_valued_map")
@Setter
@Getter
@ToString
public class MultiValuedConfigMap implements Serializable{
	@Id
	private String key;
	private String type;
	private Map<String, List<String>> value;


} 
