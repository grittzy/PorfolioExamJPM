package com.portfolio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portfolio.vo.Fund;
import com.portfolio.vo.FundWeight;
import com.portfolio.vo.Funds;

/**
 * 
 * @author Joed Cueto
 *
 */
public class FundService {
	
	/**
	 * Group the funds by parents
	 * @param funds
	 * @return
	 */
	public Map<String, List<Fund>> manageFundsByParentId(List<Fund> funds) {
		Map<String, List<Fund>> groupedDataByParentId = new HashMap<String, List<Fund>>();
		
		for (Fund fund : funds) {
			
			String currentKey = fund.getFundParentId();
			if (groupedDataByParentId.get(currentKey) != null) {
				List<Fund> curFunds = groupedDataByParentId.get(currentKey);
				curFunds.add(fund);
				groupedDataByParentId.put(currentKey, curFunds);
			} else {
				List<Fund> childFunds = new ArrayList<Fund>();
				childFunds.add(fund);
				groupedDataByParentId.put(currentKey, childFunds);
			}
		}
		
		return groupedDataByParentId;
	}
	
	
	/**
	 * Generate records of parent aggregate market values
	 * @param groupedFunds
	 * @return
	 */
	public Map<String, Double> generateParentFundAggregateValue(Map<String, List<Fund>> groupedFunds) {
		
		Map<String, Double> parentAggregateFundValue = new HashMap<String, Double>();
		
		for (Map.Entry<String, List<Fund>> entry : groupedFunds.entrySet()) {
		    Double aggFund = 0.0;
		    for(Fund fund : entry.getValue()) {
		    	aggFund = aggFund + fund.getValue();
		    }
		    
		    parentAggregateFundValue.put(entry.getKey(), aggFund);
		}
		
		return parentAggregateFundValue;
	}
	
	/**
	 * Get fund weights of funds
	 * @param groupedFundsByParentId
	 * @param parentFundAggregateValue
	 * @return
	 */
	public List<FundWeight> getFundWeights(Map<String, List<Fund>> groupedFundsByParentId, Map<String, Double> parentFundAggregateValue) {
		List<FundWeight> fundWeights = new ArrayList<FundWeight>();
		
		for (Map.Entry<String, List<Fund>> entry : groupedFundsByParentId.entrySet()) {
			for(Fund fund : entry.getValue()) {
				
				Funds leafFunds = getLeafFunds(groupedFundsByParentId, fund);
				for(Fund leafFund : leafFunds.getFunds()) {
					FundWeight fWeight = new FundWeight();
		    		fWeight.setChild(leafFund.getFundId());
		    		fWeight.setParent(entry.getKey());
		    		fWeight.setMarketValue(leafFund.getValue() / (parentFundAggregateValue.get(entry.getKey())));
		    		fundWeights.add(fWeight);
				}
				
		    	
		    }
		}
		
		
		return fundWeights;
	}
	
	private Funds getLeafFunds(Map<String, List<Fund>> groupedFundsByParentId, Fund currentFund) {
		
		Funds fundList = new Funds();
		
		Fund returnFund = null;
		
		if(groupedFundsByParentId.get(currentFund.getFundId())!=null) {
			
			List<Fund> funds = groupedFundsByParentId.get(currentFund.getFundId());
			for(Fund fund : funds) {
				returnFund = getLeafFund(groupedFundsByParentId, fund);
				fundList.getFunds().add(returnFund);
			}
		} else {
			fundList.getFunds().add(currentFund);
		}
		
		return fundList;
	}
	
	
	private Fund getLeafFund(Map<String, List<Fund>> groupedFundsByParentId, Fund currentFund) {
	
		Fund returnFund = null;
		
		if(groupedFundsByParentId.get(currentFund.getFundId())!=null) {
			
			List<Fund> funds = groupedFundsByParentId.get(currentFund.getFundId());
			for(Fund fund : funds) {
				returnFund = getLeafFund(groupedFundsByParentId, fund);
			}
		} else {
			returnFund = currentFund;
		}
		
		return returnFund;
	}
	
	

}
