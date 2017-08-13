package com.chitmaster.service;

import java.util.List;

import com.chitmaster.entity.ChitGroup;

public interface ChitGroupService {
	
	public List<String> createChitGroupAndInitialize(ChitGroup chitGroup, List<String> emailIdList);
	
	public List<String> retrieveMissingUserList(List<String> emailIdList);
}
