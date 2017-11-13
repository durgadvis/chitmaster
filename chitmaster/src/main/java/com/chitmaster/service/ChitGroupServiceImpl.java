package com.chitmaster.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.chitmaster.dto.ChitGroupPremiumDetailsDto;
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

	public List<String> createChitGroupAndInitialize(ChitGroup chitGroup, List<String> emailIdList) {
		setDefaultParameters(chitGroup, emailIdList.size());
		persistenceManager.saveObject(chitGroup);
		
		List<String> currentEmailIdList = entityManager.getEmailIdListOfCurrentUsers();
		
		for (String currentEmailId : currentEmailIdList) {
			Register register = entityManager.getUserByEmailId(currentEmailId);
			User user = createAndSaveUser(chitGroup, register);
			createInitialPremiumDetailsForUser(chitGroup, user);
		}
		
		emailIdList.removeAll(currentEmailIdList);
		return emailIdList;
	}

	private void setDefaultParameters(ChitGroup chitGroup, int numberOfMembers) {
		chitGroup.setGroupActive(true);
		chitGroup.setNumberOfMembers(numberOfMembers);
	}

	private PremiumDetails createInitialPremiumDetailsForUser(ChitGroup chitGroup, User user) {
		PremiumDetails premiumDetails = new PremiumDetails();
		premiumDetails.setChitGroup(chitGroup);
		premiumDetails.setTotalAmountToBePaid(chitGroup.getChitValue());
		premiumDetails.setUser(user);
		premiumDetails.setMonthlyBiddingDiscountAmount(0.0);
		premiumDetails.setMonthlyPremium(chitGroup.getChitValue()/chitGroup.getNumberOfMembers());
		
		persistenceManager.saveObject(premiumDetails);
		return premiumDetails;
	}

	private User createAndSaveUser(ChitGroup chitGroup, Register register) {
		User userToAdd = new User();
		userToAdd.setChitGroup(chitGroup);
		userToAdd.setCompletedBidding(false);
		userToAdd.setHost(false);
		userToAdd.setRegister(register);

		persistenceManager.saveObject(userToAdd);
		return userToAdd;
	}

	public List<String> retrieveMissingUserList(List<String> emailIdList) {
		List<String> currentEmailIdList = entityManager.getEmailIdListOfCurrentUsers();
		emailIdList.removeAll(currentEmailIdList);
		return emailIdList;
	}

	@Override
	public List<ChitGroup> getChitGroupList(String emailId) {
		return entityManager.getGroupListbyEmailId(emailId);
	}
	
	public ChitGroup getChitGroupDetail(int chitGroupId) {
		return entityManager.getChitGroupById(chitGroupId);
	}
	
	public List<PremiumDetails> getChitGroupPremiumDetail(int chitGroupId) {
		return entityManager.getPremiumDetailsForUserByChitGroup(chitGroupId);
	}

	@Override
	public ChitGroupPremiumDetailsDto getChitGroupCompleteDetails(int chitGroupId) {
		ChitGroup chitGroupDetail = getChitGroupDetail(chitGroupId);
		List<PremiumDetails> listPremiumDetails = getChitGroupPremiumDetail(chitGroupId);
		ChitGroupPremiumDetailsDto chitGroupPremDetails = populateGroupAndPremiumDetailsDto(chitGroupDetail, listPremiumDetails);
		return chitGroupPremDetails;
	}

	private ChitGroupPremiumDetailsDto populateGroupAndPremiumDetailsDto(ChitGroup chitGroupDetail,
			List<PremiumDetails> listPremiumDetails) {
		ChitGroupPremiumDetailsDto dto = new ChitGroupPremiumDetailsDto();

		dto.setChitGroupId(chitGroupDetail.getChitGroupId());
		dto.setName(chitGroupDetail.getChitGroupName());
		dto.setChitValue(chitGroupDetail.getChitValue());
		dto.setCommissionPercent(chitGroupDetail.getCommissionPercent());
		dto.setMinPercentBidding(chitGroupDetail.getMinPercentBidding());
		dto.setMaxPercentBidding(chitGroupDetail.getMaxPercentBidding());
		dto.setDateofBidding(chitGroupDetail.getDateofBidding());
		dto.setStartDate(chitGroupDetail.getStartDate());
		dto.setEndDate(chitGroupDetail.getEndDate());
		dto.setListPremiumDetails(listPremiumDetails);
		

		
		return dto;
	}

}
