package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	
	public List<Order> findByCustomerId(Integer id);

}
