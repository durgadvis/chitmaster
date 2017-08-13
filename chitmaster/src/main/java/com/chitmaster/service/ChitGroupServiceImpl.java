package com.chitmaster.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.chitmaster.entity.ChitGroup;
import com.chitmaster.entity.PremiumDetails;
import com.chitmaster.entity.Register;
import com.chitmaster.entity.User;
import com.chitmaster.persistence.ObjectEntityManager;
import com.chitmaster.persistence.PersistenceManager;

@Component
public class ChitGroupServiceImpl implements ChitGroupService {

	@Inject
	private PersistenceManager persistenceManager;

	@Inject
	private ObjectEntityManager entityManager;

	@Override
	public List<String> createChitGroupAndInitialize(ChitGroup chitGroup, List<String> emailIdList) {
		setDefaultParameters(chitGroup, emailIdList.size());
		persistenceManager.saveObject(chitGroup);
		
		List<String> currentEmailIdList = entityManager.getEmailIdListOfCurrentUsers();
		
		for (String currentEmailId : currentEmailIdList) {
			Register register = entityManager.getUserByEmailId(currentEmailId);
			User user = createAndSaveUser(chitGroup, register);
			createPremiumDetailsForUser(chitGroup, user);
		}
		
		emailIdList.removeAll(currentEmailIdList);
		return emailIdList;
	}

	private void setDefaultParameters(ChitGroup chitGroup, int numberOfMembers) {
		chitGroup.setGroupActive(true);
		chitGroup.setNumberOfMembers(numberOfMembers);
	}

	private PremiumDetails createPremiumDetailsForUser(ChitGroup chitGroup, User user) {
		PremiumDetails premiumDetails = new PremiumDetails();
		premiumDetails.setChitGroup(chitGroup);
		premiumDetails.setTotalAmountToBePaid(chitGroup.getChitValue());
		premiumDetails.setUser(user);
		
		persistenceManager.saveObject(premiumDetails);
		return premiumDetails;
	}

	private User createAndSaveUser(ChitGroup chitGroup, Register register) {
		User userToAdd = new User();
		userToAdd.getChitGroups().add(chitGroup);
		userToAdd.setCompletedBidding(false);
		userToAdd.setHost(false);
		userToAdd.setRegister(register);

		persistenceManager.saveObject(userToAdd);
		return userToAdd;
	}

	@Override
	public List<String> retrieveMissingUserList(List<String> emailIdList) {
		List<String> currentEmailIdList = entityManager.getEmailIdListOfCurrentUsers();
		emailIdList.removeAll(currentEmailIdList);
		return emailIdList;
	}
	
	

}
