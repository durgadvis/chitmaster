package com.chitmaster.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chitmaster.entity.BiddingDetails;
import com.chitmaster.persistence.ObjectEntityManager;
import com.chitmaster.persistence.PersistenceManager;

@Component
public class BiddingServiceImpl {

	@Autowired
	private ObjectEntityManager entityManager;
	
	@Autowired
	private PersistenceManager persistenceManager;

	private Map<Integer, Map<String, Double>> globalBidMap = new ConcurrentHashMap<Integer, Map<String, Double>>();
	
	private Map<Integer, Boolean> globalBidUpdatedFlagMap = new ConcurrentHashMap<Integer, Boolean>();
	
	public Map<String, Double> getInitialBiddingMap(int chitGroupId) {
		
		if (globalBidMap.get(chitGroupId) == null) {
			Map<String, Double> biddingMap = new ConcurrentHashMap<String, Double>();
			double minPercent = entityManager.getMinPercentBidding(chitGroupId);
			System.out.println("Min Percent for this group is " + minPercent);
			List<String> userIds= entityManager.getUserIdByChitGroup(chitGroupId);
			for(String userId: userIds){
				System.out.println("Inserting emailId " + userId + " with min percent as " + minPercent);
				biddingMap.put(userId, minPercent);
			}
			globalBidMap.put(chitGroupId, biddingMap);
		}
		return globalBidMap.get(chitGroupId);
	}
	
	public void updateBidDetails(int chitGroupId, String userId, double updatedBidValue) {
		Map<String, Double> groupSpecificBidMap = globalBidMap.get(chitGroupId);
		groupSpecificBidMap.put(userId, updatedBidValue);
		globalBidMap.put(chitGroupId, groupSpecificBidMap);
		globalBidUpdatedFlagMap.put(chitGroupId, true);
	}

	public Map<String, Double> getUpdatedBidMap(int chitGroupId) {
		return globalBidMap.get(chitGroupId);
	}

	public boolean isBidMapUpdated(int chitGroupId) {
		return globalBidUpdatedFlagMap.getOrDefault(chitGroupId, false);
	}

	public String getHighestBidder(int chitGroupId) {
		Map<String, Double> groupSpecificBidMap = globalBidMap.get(chitGroupId);
		double highestValue = 0;
		String highestBidder = null;
		
		for(Entry<String, Double> userValue : groupSpecificBidMap.entrySet()) {
			if (userValue.getValue() > highestValue) {
				highestValue = userValue.getValue();
				highestBidder = userValue.getKey();
			}
		}
		return highestBidder;
	}

	public void updateWinner(int chitGroupId, String userId, double winningBidValue) {
		BiddingDetails biddingDetails = new BiddingDetails();
		biddingDetails.setBiddingPercentWon(winningBidValue);
		biddingDetails.setChitGroup(entityManager.getChitGroupById(chitGroupId));
		biddingDetails.setWinnerUser(entityManager.getUserByChitGroupAndEmailId(chitGroupId, userId));
		persistenceManager.saveObject(biddingDetails);
		
		globalBidMap.remove(chitGroupId);
		globalBidUpdatedFlagMap.remove(chitGroupId);
	}
}
