package com.chitmaster.controllers;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chitmaster.dto.BiddingDetailsDto;
import com.chitmaster.service.BiddingServiceImpl;

@RestController
public class BiddingController {

	@Inject
	BiddingServiceImpl biddingService;

	@GetMapping("/getInitialBidding")
	public @ResponseBody Map<String, Double> getInitialBiddingDetails(@RequestParam	int chitGroupId) {
		System.out.println("Entered get Initial Bidding Map");
		return biddingService.getInitialBiddingMap(chitGroupId);		
	}

	@PostMapping("/updateBidValue")
	public void updateBiddingDetailsMap(@RequestBody BiddingDetailsDto biddingDetails) {
		biddingService.updateBidDetails(biddingDetails.getChitGroupId(), 
				biddingDetails.getUserId(), biddingDetails.getBidValue());
	}
	
	@GetMapping("/getUpdatedBidMap")
	public @ResponseBody Map<String, Double> getUpdatedBidMap(@RequestParam	int chitGroupId) {
		return biddingService.getUpdatedBidMap(chitGroupId);
	}
	
	@GetMapping("/isBidMapUpdated")
	public @ResponseBody boolean isBidMapUpdated(@RequestParam int chitGroupId) {
		return biddingService.isBidMapUpdated(chitGroupId);
	}
	
	@GetMapping("/getHighestBidder")
	public @ResponseBody String getHighestBidder(@RequestParam int chitGroupId) {
		return biddingService.getHighestBidder(chitGroupId);
	}
	
	@PostMapping("/updateWinner")
	public void updateWinner(@RequestBody BiddingDetailsDto biddingDetails) {
		biddingService.updateWinner(biddingDetails.getChitGroupId(), 
				biddingDetails.getUserId(), biddingDetails.getBidValue());
	}
}
