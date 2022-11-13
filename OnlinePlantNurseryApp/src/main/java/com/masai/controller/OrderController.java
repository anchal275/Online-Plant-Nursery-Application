package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.OutOfStockException;
import com.masai.exceptions.ProductException;
import com.masai.model.Order;
import com.masai.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService oService;
	
	@PostMapping("order/{transactionMode}")
	public ResponseEntity<String> placeOrderHandler(@PathVariable String transactionMode,@RequestParam String key) throws LoginException, CartException, OutOfStockException, CustomerException, ProductException{
		 String message = oService.placeOrder(transactionMode, key);
		 return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("order/{orderId}")
    public ResponseEntity<String> cancelOrderHandler(@PathVariable Integer orderId, @RequestParam String key) throws CustomerException, ProductException, LoginException, OrderException{
    	String message = oService.cancelOrder(key, orderId);
    	return new ResponseEntity<String>(message,HttpStatus.CREATED);
    }
	
	
	   
    @GetMapping("order/{customerId}")
    public ResponseEntity<List<Order>> viewOrderHandler(@PathVariable Integer customerId, @RequestParam String key) throws CustomerException, ProductException, LoginException, OrderException{
    	List<Order> listorder = oService.viewOrder(key, customerId);
    	return new ResponseEntity<List<Order>>(listorder,HttpStatus.CREATED);
    }
    
    
    @GetMapping("order")
    public ResponseEntity<List<Order>> viewAllOrderHandler(@RequestParam String key) throws CustomerException, ProductException, LoginException, OrderException{
    	List<Order> allOrder = oService.viewAllOrders(key);
    	return new ResponseEntity<List<Order>>(allOrder,HttpStatus.CREATED);
    }
	
}
