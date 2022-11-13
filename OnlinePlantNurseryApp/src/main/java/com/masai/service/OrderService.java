package com.masai.service;

import java.util.List;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.OutOfStockException;
import com.masai.model.Order;


public interface OrderService {
	
	public String placeOrder(String transactionMode, String key) throws LoginException,CartException,OutOfStockException;
	public String cancelOrder(String key,Integer orderId) throws LoginException,OrderException;
	public List<Order> viewOrder(String key ,Integer customerId) throws LoginException,OrderException,CustomerException;
	public List<Order> viewAllOrders(String key) throws LoginException , OrderException;

}
