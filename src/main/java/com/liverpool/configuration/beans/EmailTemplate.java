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
	@DisplayProperty(display = true,uiPropType = "String", order=1)
	public String templateName;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String", order=2)
	public String subject;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String", order=3)
	public String header;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String", order=4)
	public String body;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "String", order=5)
	public String footer;
}
