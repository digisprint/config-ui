package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "static_keys")
@Setter
@Getter
@ToString
public class StaticKeys implements Serializable{
	
	@Id
	private String key;
	private String value;
	private Map<String, String> siteValues;

}
