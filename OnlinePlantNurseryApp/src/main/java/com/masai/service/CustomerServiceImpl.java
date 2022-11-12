package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		Customer existingCustomer = customerRepo.findByUserName(customer.getUserName());
		if (existingCustomer != null) {
			throw new CustomerException("Username: " + customer.getUserName() + " already exists");
		}
		Customer existingCustomer2 = customerRepo.findByEmail(customer.getEmail());
		if (existingCustomer2 != null) {
			throw new CustomerException("Email: " + customer.getEmail() + " already exists");
		}
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException, LoginException {
		CurrentUserSession user = sessionRepo.findByKey(key);
		if (user == null)
			throw new LoginException("Please provide a valid key");
		if (customer.getCustomerId() == user.getUserId()) {
			return customerRepo.save(customer);
		} else {
			throw new CustomerException("Invalid customer credentials");
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String key) throws CustomerException, LoginException {
		Optional<Customer> customer = customerRepo.findById(customerId);
		if(customer.isPresent()) {
			if(key.equals("admin")) {
				customerRepo.delete(customer.get());
				return customer.get();
			}else {
				CurrentUserSession user = sessionRepo.findByKey(key);
				if(user==null) {
					throw new LoginException("Please provide a valid key");
				}else if (user.getUserId()==customer.get().getCustomerId()) {
					customerRepo.delete(customer.get());
					return customer.get();
				}else {
					throw new CustomerException("Invalid customer Id");
				}
			}
		}else {
			throw new CustomerException("Invalid customer Id please provide a valid customer Id");
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException, LoginException {
		Optional<Customer> customer = customerRepo.findById(customerId);
		if(customer.isPresent()) {
			if(key.equals("admin")) {
				return customer.get();
			}else {
				CurrentUserSession user = sessionRepo.findByKey(key);
				if(user==null) {
					throw new LoginException("Please provide a valid key");
				}else if (user.getUserId()==customer.get().getCustomerId()) {
					return customer.get();
				}else {
					throw new CustomerException("Invalid customer Id");
				}
			}
		}else {
			throw new CustomerException("Invalid customer Id please provide a valid customer Id");
		}
	}

	@Override
	public List<Customer> viewAllCustomer(String key) throws CustomerException, LoginException {
		if(!key.equals("admin")) {
			throw new CustomerException("Please provide a valid key");
		}else {
			List<Customer> customers = customerRepo.findAll();
			if(customers.size()==0) {
				throw new CustomerException("No customer found");
			}else {
				return customers;
			}
		}
	}
	
	

}
