package com.chitmaster.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chitmaster.dto.ChitGroupPremiumDetailsDto;
import com.chitmaster.entity.ChitGroup;
import com.chitmaster.security.ChitMasterJwtTokenUtil;
import com.chitmaster.security.JwtTokenMissingException;
import com.chitmaster.service.ChitGroupService;

@RestController
public class ChitGroupController {

	@Inject
	private ChitGroupService chitGroupService;
	
	@Inject
	private ChitMasterJwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/getname")
	public String getName(){
		return "Vishal-Shruthi.";
	}
	
	@PostMapping("/createGroup")
	public @ResponseBody List<String> createChitGroup(@RequestBody ChitGroup chitGroup, String csvEmailIdList) {
		List<String> emailIdList = new ArrayList<String>();
		if (csvEmailIdList != null) {
			 emailIdList = Arrays.asList(csvEmailIdList.split(","));
		}
		System.out.println("Here is the info" +chitGroup.getChitGroupName() + chitGroup.toString() );
		return chitGroupService.createChitGroupAndInitialize(chitGroup, emailIdList);
	}
	
	@GetMapping("/getGroups")
	public @ResponseBody List<ChitGroup> getChitGroups(HttpServletRequest request) {
		String emailId = jwtTokenUtil.getUserNameFromRequest(request);
		System.out.println("Retrieved emailId " + emailId);
		return chitGroupService.getChitGroupList(emailId);
	}
	
	@GetMapping("/getGroupDetail")
	public @ResponseBody ChitGroupPremiumDetailsDto getChitGroupDetail(@RequestParam("id") int id, HttpServletRequest request) {
		System.out.println("Entered Chit Group Detail for id = " + id);
		return chitGroupService.getChitGroupCompleteDetails(id);
	}
}
