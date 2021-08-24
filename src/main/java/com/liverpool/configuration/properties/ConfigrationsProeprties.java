package com.liverpool.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Configuration
public class ConfigrationsProeprties {

	@Value("${liverpool.configuration.secretKey}")
	private String secretKey;

}
