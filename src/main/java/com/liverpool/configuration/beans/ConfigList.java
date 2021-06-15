package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "config_list")
@Setter
@Getter
@ToString
public class ConfigList implements Serializable{
	@Id
	private String key;
	private List<String> value;
}
