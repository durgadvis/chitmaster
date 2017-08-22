package com.chitmaster.service;

import com.chitmaster.entity.Register;

public interface LoginService {

	public boolean isUserCredentialValid(String userId, String password);
	public void addUser(Register user);
	
}
