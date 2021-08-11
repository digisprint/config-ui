package com.liverpool.configuration.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liverpool.configuration.annotations.BeanConfiguration;
import com.liverpool.configuration.annotations.DisplayProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "config_date")
@Setter
@Getter
@ToString
@BeanConfiguration(name="Date Configurations",url_path="configDate", accessPrivilegeName="configdate")
@Component
public class ConfigDate implements Serializable{
	@Id
	@NotBlank(message = "Key should not be empty")
	@DisplayProperty(display = true,uiPropType = "String", order=1, objectKey = true)
	private String key;
	
	@NotEmpty(message = "Value should not be empty")
	@DisplayProperty(uiPropType = "List<Date>", order=2)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private List<Date> value;
	
	@DisplayProperty(uiPropType = "Boolean", order=3)
	private boolean saturdayHoliday;
	
	@DisplayProperty(uiPropType = "Boolean", order=4)
	private boolean sundayHoliday;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@DisplayProperty(uiPropType = "Date", order=5)
	private Date dateOfBirth;
}
