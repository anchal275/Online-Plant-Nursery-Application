package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.LoginDTO;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.userRepo;

import net.bytebuddy.utility.RandomString;

public class LoginLogoutServiceImp implements LoginLogoutService{
	
	
	
	@Autowired
	private userRepo ur;
	
	
	
	@Autowired
	private CurrentUserSessionRepo cusr;
	
	
	
	
	
	
	
	

	@Override
	public String logIntoAccount(LoginDTO dto) throws Exception {

		Customer c=ur.findByUserName(dto.getUserName());
		
		if(c==null) {
			throw new Exception("please Enter Valid UserName");
		}
		
		
		Optional<CurrentUserSession> validCustomerSession=cusr.findById(c.getCustomerId());
		
		
		if(validCustomerSession.isPresent()) {
			throw new Exception("User already logedIn with this userName: ");
		}
		
		
		
		if(c.getPassword().equals(dto.getPassword())) {
			
			String key = RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(c.getCustomerId(),key,LocalDateTime.now());
			
			cusr.save(currentUserSession);
			
			return currentUserSession.toString();
		}
		else
			throw new Exception("Please enter valid password");
		

	}

	
	
	

	
	@Override
	public String logOutFromAccount(String key) throws Exception {

		CurrentUserSession validCustomerSession = cusr.findByKey(key);
		
		if(validCustomerSession==null) {
			throw new Exception("User Not logged In with this userName: ");
		}
		
		cusr.delete(validCustomerSession);
		
		return "Loged Out: ";
	}

	
	
	
	
	
	
	
	
	
	
	
}
