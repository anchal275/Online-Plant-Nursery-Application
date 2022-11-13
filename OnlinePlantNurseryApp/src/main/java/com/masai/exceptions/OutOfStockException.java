package com.masai.exceptions;

public class OutOfStockException extends Exception{
	
	public OutOfStockException(){
		
	}

	public OutOfStockException(String msg) {
		super(msg);
	}
}
