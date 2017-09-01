package com.chitmaster.service;

import com.chitmaster.dto.UserDto;
import com.chitmaster.entity.Register;
import com.chitmaster.security.ChitMasterJwtUser;

public interface LoginService {

	public boolean isUserCredentialValid(String userId, String password);
	public void addUser(Register user);
	public ChitMasterJwtUser generateTokenOnLogin(String username, String password) throws Exception;

}
