package com.liverpool.configuration.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {
	
	private StaticKeys staticKeys;
	private ConfigList configList;
	private ConfigMap configMap;
	private MultiValuedConfigMap multiValuedConfigMap;

}
