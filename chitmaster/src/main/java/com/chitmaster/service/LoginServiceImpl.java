package com.chitmaster.service;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chitmaster.entity.Register;
import com.chitmaster.persistence.ObjectEntityManager;
import com.chitmaster.persistence.PersistenceManager;
import com.chitmaster.security.ChitMasterJwtAuthenticationProvider;
import com.chitmaster.security.ChitMasterJwtTokenUtil;
import com.chitmaster.security.ChitMasterJwtUser;

@Component
public class LoginServiceImpl implements LoginService {

	@Inject
	private PersistenceManager persistenceManager;
	
	@Inject
	private ObjectEntityManager entityManager;
	
	@Inject
	private ChitMasterJwtAuthenticationProvider authenticationManager;

	@Inject
	private ChitMasterJwtTokenUtil jwtTokenUtil;
	
	public boolean isUserCredentialValid(String userId, String password) {
		Register retrievedUser = entityManager.getUserByEmailId(userId);
		if (retrievedUser != null && retrievedUser.getPassword().equals(password)) {
			return true;
		}		
		return false;
	}

	public void addUser(Register user) {
		Register register = entityManager.getUserByEmailId(user.getEmailId());
		if (register == null) {
			persistenceManager.saveObject(user);			
		}
	}
	
	public ChitMasterJwtUser generateTokenOnLogin(String username, String password) throws Exception {
		ChitMasterJwtUser user=null;
		try {
			user = new ChitMasterJwtUser(username, password);
			jwtTokenUtil.createAuthoritiesForUser(user);
			final String generatedToken = jwtTokenUtil.generateTokenForUser(user);
			user.setToken(generatedToken);
			
			final Authentication authentication = authenticationManager.authenticate(jwtTokenUtil.createJwtAuthToken(user));
	        SecurityContextHolder.getContext().setAuthentication(authentication);

			return user;
		} catch (Exception exception) {
			throw new Exception("Unauthorized" + exception);
		}
	}
}
