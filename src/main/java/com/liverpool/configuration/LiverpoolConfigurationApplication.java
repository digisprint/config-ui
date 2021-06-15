package com.liverpool.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LiverpoolConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiverpoolConfigurationApplication.class, args);
	}

}
