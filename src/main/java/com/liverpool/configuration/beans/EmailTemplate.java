package com.liverpool.configuration.beans;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.annotations.DisplayProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "email_template")
@Setter
@Getter
@ToString
@BeanConfiguration(name="Email Templates",url_path="emailTemplate", accessPrivilegeName="emailTemplate")
@Component
public class EmailTemplate implements Serializable{

	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String")
	public String templateName;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String")
	public String subject;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String")
	public String header;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String")
	public String body;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String")
	public String footer;
}
