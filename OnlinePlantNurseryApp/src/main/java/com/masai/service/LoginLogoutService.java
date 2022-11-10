package com.masai.service;

import com.masai.model.LoginDTO;

public interface LoginLogoutService {
	
	public String logIntoAccount(LoginDTO dto)throws Exception;
	
	public String logOutFromAccount(String key)throws Exception;

}
