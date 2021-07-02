package com.liverpool.configuration.beans;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationTypes {
	private String beanName;
	private String displayName;
	private String displayProperty;
	private Map<String, String> properties;
}
