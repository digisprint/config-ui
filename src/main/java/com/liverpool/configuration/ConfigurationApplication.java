package com.liverpool.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConfigurationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationApplication.class, args);
	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**") .allowedMethods("HEAD", "GET", "PUT", "POST",
	 * "DELETE", "PATCH")
	 * .allowedOrigins("http://localhost:3000","http://35.224.205.52:3000")
	 * //.allowedOrigins("*" ) .allowCredentials(true); } }; }
	 */
}
