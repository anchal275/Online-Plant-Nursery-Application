package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDTO {
	@NotNull(message = "userName can not be null")
	@NotBlank(message = "userName can not be blank")
	private String userName;
	
	@NotNull(message = "Password can not be null")
	@NotBlank(message = "Password can not be blank")
	private String password;

}