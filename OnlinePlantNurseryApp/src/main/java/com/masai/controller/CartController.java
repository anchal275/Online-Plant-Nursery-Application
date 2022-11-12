package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Cart;
import com.masai.service.CartService;


@Controller
public class CartController {
	
	@Autowired
	private CartService cService;
	
    @PostMapping("cart/{productId}")
	public ResponseEntity<String> addToCartHandler(@PathVariable Integer productId,@RequestParam String key) throws CustomerException, ProductException, LoginException{
		  String message = cService.addToCart(productId, key);
		  return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
    
    @DeleteMapping("cart/{productId}")
    public ResponseEntity<String> deleteFromCartHandler(@PathVariable Integer productId,@RequestParam String key) throws CustomerException, ProductException, LoginException{
    	String message = cService.deleteFromCart(productId, key);
    	return new ResponseEntity<String>(message,HttpStatus.CREATED);
    }
    
    @GetMapping("cart")
    public ResponseEntity<Cart> viewCartHandler(@RequestParam String key) throws CustomerException, ProductException, LoginException{
    	Cart cart = cService.viewCart(key);
    	return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
    }
    
    @DeleteMapping("cart")
    public ResponseEntity<String> emptyCartHandler(@RequestParam String key) throws CustomerException, ProductException, LoginException{
    	String message = cService.emptyCart(key);
    	return new ResponseEntity<String>(message,HttpStatus.CREATED);
    }
    
    
    
    
    
	
	

}
