package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	
	Integer addressId;
	String houseNo;
	String colony;
	String city;
	String state;
	Integer pincode;
	
}
