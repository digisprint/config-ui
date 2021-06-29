package com.liverpool.configuration.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {
	@Valid
	private StaticKeys staticKeys;
	@Valid
	private ConfigList configList;
	@Valid
	private ConfigMap configMap;
	@Valid
	private MultiValuedConfigMap multiValuedConfigMap;

}
