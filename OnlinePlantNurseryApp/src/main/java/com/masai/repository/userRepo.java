package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface userRepo extends JpaRepository<Customer, Integer> {
	
	public Customer findByUserName(String userName);

}
