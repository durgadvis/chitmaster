package com.chitmaster.persistence;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chitmaster.entity.ChitGroup;
import com.chitmaster.entity.PremiumDetails;
import com.chitmaster.entity.Register;
import com.chitmaster.entity.User;

@Component
public class ObjectEntityManager {
	
	@Autowired
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
	
	public PremiumDetails getChitGroupPremiumById(int chitGroupId) {
		PremiumDetails premiumDetails = persistenceManager.readObject(PremiumDetails.class, chitGroupId);
		return premiumDetails;
	}
	
	
	public List<PremiumDetails> getAllPremiumDetailsForUser(User user) {
		Criterion userCriteria = Restrictions.eq("user.userId", user.getUserId());
		List<PremiumDetails> premiumDetails = persistenceManager.readObjectWithCriteria(PremiumDetails.class, userCriteria);
		return premiumDetails;
	}

	public List<PremiumDetails> getAllPremiumDetailsByChitGroupId(int chitGroupId) {
		Criterion criteria = Restrictions.eq("chitGroup.chitGroupId", chitGroupId);
		List<PremiumDetails> premiumDetails = persistenceManager.readObjectWithCriteria(PremiumDetails.class, criteria);
		return premiumDetails;
	}
	
	public List<PremiumDetails> getPremiumDetailsForUserByChitGroup(int chitGroupId) {
		List<PremiumDetails> premiumDetailsList = getAllPremiumDetailsByChitGroupId(chitGroupId);
		
		return premiumDetailsList;
	}
	
	public List<String> getEmailIdListOfCurrentUsers() {
		return persistenceManager.getByProjection(Register.class, "emailId");
	}
	
	public List<ChitGroup> getGroupListbyEmailId(String emailId){
		String sql = "SELECT cg FROM ChitGroup cg WHERE cg.chitGroupId IN (SELECT u.chitGroup.chitGroupId FROM User u WHERE emailId='" 
					+ emailId +"')";
		System.out.println(sql);
		List<ChitGroup> groupIdNameList = persistenceManager.getByQuery(ChitGroup.class, sql);
		return groupIdNameList;
	}

	public Double getMinPercentBidding(int chitGroupId) {
		String sql = "SELECT cg FROM ChitGroup cg WHERE cg.chitGroupId = " + chitGroupId;
		List<ChitGroup> chitGroupList = persistenceManager.getByQuery(ChitGroup.class, sql);
		if (!chitGroupList.isEmpty()) {
			return chitGroupList.get(0).getMinPercentBidding();
		}
		return null;
	}
	
	public List<String> getUserIdByChitGroup(int chitGroupId) {
		Criterion criterion = Restrictions.eq("chitGroup.chitGroupId", chitGroupId);
		return persistenceManager.getByCriteriaAndProjection(User.class, "register.emailId", criterion);
	}

	public User getUserByChitGroupAndEmailId(int chitGroupId, String emailId) {
		Criterion chitGroupCriterion = Restrictions.eq("chitGroup.chitGroupId", chitGroupId);
		Criterion userIdCriterion = Restrictions.eq("register.emailId", emailId);
		LogicalExpression logicalExpression = Restrictions.and(chitGroupCriterion, userIdCriterion);
		List<User> userList = persistenceManager.readObjectWithCriteria(User.class, logicalExpression);
		
		if (!userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}
	
}
 