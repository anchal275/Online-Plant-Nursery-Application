package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
	//plantException
	@ExceptionHandler(PlantException.class)
	public ResponseEntity<MyErrorBean> PlantExceptionHandler(PlantException ie,WebRequest wr){
		
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ie.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	//SeedException
	@ExceptionHandler(SeedException.class)
	public ResponseEntity<MyErrorBean> SeedExceptionHandler(SeedException ie,WebRequest wr){
		
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ie.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
		
		

	//globalException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorBean> GlobalExceptionHandler(Exception ie,WebRequest wr){
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ie.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	//to handle Not found exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorBean> myNotFoundHandler(NoHandlerFoundException nfe,WebRequest wr) {
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(nfe.getMessage());
		error.setDetails(wr.getDescription(false));
	
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	//validation exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorBean> validationExceptionHandler(MethodArgumentNotValidException ie) {
	
		MyErrorBean validatorError = new MyErrorBean();
		
		validatorError.setTimestamp(LocalDateTime.now());
		validatorError.setMessage("Validation error");
		validatorError.setDetails(ie.getBindingResult().getFieldError().getDefaultMessage());
	
	return new ResponseEntity<>(validatorError,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorBean> customerExceptionHandler(CustomerException ce, WebRequest req){
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDetails(req.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorBean> cartExceptionHandler(CartException ce, WebRequest req){
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDetails(req.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorBean> orderExceptionHandler(OrderException oe, WebRequest req){
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(oe.getMessage());
		error.setDetails(req.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorBean> addressExceptionHandler(AddressException ae, WebRequest req){
		MyErrorBean error = new MyErrorBean();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ae.getMessage());
		error.setDetails(req.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
