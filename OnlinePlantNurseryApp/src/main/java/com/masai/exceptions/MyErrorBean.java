package com.masai.exceptions;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyErrorBean {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	

}
