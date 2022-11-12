package com.masai.service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Cart;

public interface CartService {
	
	public String addToCart(Integer productId, String key) throws CustomerException,ProductException, LoginException;
	
	public String deleteFromCart(Integer productId, String key) throws CustomerException,ProductException, LoginException;
	
	public Cart viewCart(String key) throws CustomerException,ProductException, LoginException;
	
	public String emptyCart(String key) throws CustomerException,ProductException, LoginException;
	
	
}
