package com.chitmaster.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chitmaster.entity.ChitGroup;
import com.chitmaster.service.ChitGroupService;

@RestController
public class ChitGroupController {

	@Inject
	private ChitGroupService chitGroupService;
	
	@GetMapping("/getname")
	public String getName(){
		return "Vishal-Shruthi.";
	}
	
	@PostMapping("/createGroup")
	public @ResponseBody List<String> createChitGroup(@ModelAttribute ChitGroup chitGroup, String csvEmailIdList) {
		List<String> emailIdList = new ArrayList<String>();
		if (csvEmailIdList != null) {
			 emailIdList = Arrays.asList(csvEmailIdList.split(","));
		}
		return chitGroupService.createChitGroupAndInitialize(chitGroup, emailIdList);
	}
}
//		ChitGroup group1 = new ChitGroup();		
//		group1.setChitGroupName("Group1");
//		group1.setChitValue(100000);
//		group1.setCommissionPercent(5);
//		group1.setDateofBidding(new Date());
//		group1.setStartDate(new Date(2017, 3, 13));
//		group1.setEndDate(new Date(2017, 9, 13));
//		group1.setGroupActive(true);
//		group1.setNumberOfMembers(9);		
//		group1.setChitGroupName(chitGroup.getChitGroupName());
//		group1.setChitValue(chitGroup.getChitValue());
//		group1.setCommissionPercent(chitGroup.getCommissionPercent());
//		group1.setDateofBidding(chitGroup.getDateofBidding());
//		group1.setStartDate(new Date(2017, 3, 13));
//		group1.setEndDate(new Date(2017, 9, 13));
//		group1.setGroupActive(true);
//		group1.setNumberOfMembers(chitGroup.getNumberOfMembers());
//		
//		ChitGroup group2 = new ChitGroup();
//		group2.setChitGroupName("Group2");
//		group2.setChitValue(100000);
//		group2.setCommissionPercent(5);
//		group1.setDateofBidding(new Date());
//		group1.setStartDate(new Date(2017, 3, 13));
//		group1.setEndDate(new Date(2017, 9, 13));
//		group2.setGroupActive(true);
//		group2.setNumberOfMembers(10);
//		
//		User user1 = new User();
//		user1.setChitGroups(Utils.toList(group1, group2));
//		user1.setCompletedBidding(true);
//		
//		PremiumDetails details = new PremiumDetails();
//		details.setChitGroup(group1);
//		details.setUser(user1);
//		details.setTotalAmountToBePaid(2000);
//		
//		PremiumDetails details2 = new PremiumDetails();
//		details2.setChitGroup(group2);
//		details2.setUser(user1);
//		details2.setTotalAmountToBePaid(4000);
//		
//		persistenceManager.saveObject(group1);
//		persistenceManager.saveObject(group2);
//		persistenceManager.saveObject(user1);
//		persistenceManager.saveObject(details);
//		persistenceManager.saveObject(details2);
//		
//		user1 = null;
//		user1 = entityManager.getUserByUsername("Shruthi", "Aithal");
//		
//		System.out.println(user1.getEmailId());
//		List<ChitGroup> chitGroups = user1.getChitGroups();
//		
//		System.out.println(chitGroups.get(0).getChitGroupName());
//		System.out.println(chitGroups.get(1).getChitGroupName());
//		
//		List<PremiumDetails> premiumDetails = entityManager.getAllPremiumDetailsForUser(user1);
//		System.out.println(premiumDetails.get(0).getChitGroup().getChitGroupName());
//		System.out.println(premiumDetails.get(1).getChitGroup().getChitGroupName());
//		
//		premiumDetails = null;
//		premiumDetails = entityManager.getPremiumDetailsForUserByChitGroup(user1, group1);
//		System.out.println(premiumDetails.get(0).getChitGroup().getChitGroupName());
//		
//		premiumDetails = null;
//		premiumDetails = entityManager.getPremiumDetailsForUserByChitGroup(user1, group2);
//		System.out.println(premiumDetails.get(0).getChitGroup().getChitGroupName());
//		
//		return group1;
