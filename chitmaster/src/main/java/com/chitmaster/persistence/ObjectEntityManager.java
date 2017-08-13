package com.chitmaster.persistence;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.chitmaster.entity.ChitGroup;
import com.chitmaster.entity.PremiumDetails;
import com.chitmaster.entity.Register;
import com.chitmaster.entity.User;

@Component
public class ObjectEntityManager {
	
	@Inject
	private PersistenceManager persistenceManager;
	
	public User getUserById(int userId) {
		User user = persistenceManager.readObject(User.class, userId);
		return user;		
	}
	
	public Register getUserByUsername(String firstName, String lastName) {
		Criterion firstNameCriterion = Restrictions.eq("firstName", firstName);
		Criterion lastNameCriterion = Restrictions.eq("lastName", lastName);
		LogicalExpression logicalExp = Restrictions.and(firstNameCriterion, lastNameCriterion);
		
		List<Register> userList = persistenceManager.readObjectWithCriteria(Register.class, logicalExp);
		
		if (!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
	
	public Register getUserByEmailId(String emailId) {
		Criterion criterion = Restrictions.eq("emailId", emailId);
		
		List<Register> userList = persistenceManager.readObjectWithCriteria(Register.class, criterion);
		if (!userList.isEmpty()) {
			return userList.get(0);			
		}
		return null;
	}

	public ChitGroup getChitGroupById(int chitGroupId) {
		ChitGroup chitGroup = persistenceManager.readObject(ChitGroup.class, chitGroupId);
		return chitGroup;
	}
	
	public List<PremiumDetails> getAllPremiumDetailsForUser(User user) {
		Criterion userCriteria = Restrictions.eq("user.userId", user.getUserId());
		List<PremiumDetails> premiumDetails = persistenceManager.readObjectWithCriteria(PremiumDetails.class, userCriteria);
		return premiumDetails;
	}

	public List<PremiumDetails> getPremiumDetailsForUserByChitGroup(User user, ChitGroup chitGroup) {
		Criterion userCriterion = Restrictions.eq("user.userId", user.getUserId());
		Criterion chitGroupCriterion = Restrictions.eq("chitGroup.chitGroupId", chitGroup.getChitGroupId());
		LogicalExpression logicalExp = Restrictions.and(userCriterion, chitGroupCriterion);
		List<PremiumDetails> premiumDetails = persistenceManager.readObjectWithCriteria(PremiumDetails.class, logicalExp);
		
		return premiumDetails;
	}
	
	public List<String> getEmailIdListOfCurrentUsers() {
		return persistenceManager.getByProjection(Register.class, "emailId");
	}
}
 