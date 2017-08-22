package com.chitmaster.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.chitmaster.entity.Register;
import com.chitmaster.persistence.ObjectEntityManager;
import com.chitmaster.persistence.PersistenceManager;

@Component
public class LoginServiceImpl implements LoginService {

	@Inject
	private PersistenceManager persistenceManager;
	
	@Inject
	private ObjectEntityManager entityManager;
	
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
	
}
