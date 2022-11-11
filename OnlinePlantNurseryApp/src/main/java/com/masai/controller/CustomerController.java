package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		Customer savedCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(savedCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer,@RequestParam String key) throws CustomerException, LoginException{
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable("customerId") Integer customerId, @RequestParam String key) throws CustomerException, LoginException{
		Customer deletedCustomer = customerService.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(deletedCustomer,HttpStatus.OK);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> viewCustomerHandler(@PathVariable("customerId") Integer customerId, @RequestParam String key) throws CustomerException, LoginException{
		Customer customer = customerService.viewCustomer(customerId, key);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomerHandler(@RequestParam String key) throws CustomerException, LoginException{
		List<Customer> customers = customerService.viewAllCustomer(key);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}
