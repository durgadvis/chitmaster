package com.chitmaster.service;

import java.util.List;

import com.chitmaster.dto.ChitGroupPremiumDetailsDto;
import com.chitmaster.entity.ChitGroup;
import com.chitmaster.entity.PremiumDetails;

public interface ChitGroupService {
	
	public List<String> createChitGroupAndInitialize(ChitGroup chitGroup, List<String> emailIdList);
	
	public List<String> retrieveMissingUserList(List<String> emailIdList);
	
	public List<ChitGroup> getChitGroupList(String emailId);
	
	public ChitGroup getChitGroupDetail(int id);

	public ChitGroupPremiumDetailsDto getChitGroupCompleteDetails(int id);
}
