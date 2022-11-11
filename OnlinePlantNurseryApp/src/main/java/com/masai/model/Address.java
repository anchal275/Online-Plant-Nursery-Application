package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	
	@NotBlank(message = "House number can not be blank")
	private String houseNo;
	
	@NotBlank(message = "Colony name can not be blank")
	private String colony;
	
	@NotBlank(message = "City name can not be blank")
	private String city;
	
	@NotBlank(message = "State name can not be blank")
	private String state;
	
	@Pattern(regexp = "[1-9]{1}[0-9]{5}",message = "Pincode must of 6 digits")
	private Integer pincode;
	
	
}
