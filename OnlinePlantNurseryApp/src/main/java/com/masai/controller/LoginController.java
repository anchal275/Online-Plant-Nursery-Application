package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.model.LoginDTO;
import com.masai.service.LoginLogoutService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoginController {
	
	@Autowired
	private LoginLogoutService lls;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDto) throws LoginException{
		
		String result = lls.logIntoAccount(loginDto);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return lls.logOutFromAccount(key);
		
	}

}
